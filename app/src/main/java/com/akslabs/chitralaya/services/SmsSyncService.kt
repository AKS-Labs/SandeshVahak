package com.akslabs.SandeshVahak.services

import android.content.Context
import android.util.Log
import com.akslabs.SandeshVahak.api.BotApi
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences // Corrected import
import com.akslabs.SandeshVahak.data.localdb.entities.RemoteSmsMessage
import com.akslabs.SandeshVahak.data.localdb.entities.SmsMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import androidx.room.withTransaction

/**
 * Service responsible for synchronizing SMS messages to Telegram channel
 */
object SmsSyncService {
    private const val TAG = "SmsSyncService"
    private const val LAST_SYNC_TIMESTAMP_KEY = "last_sms_sync_timestamp"
    private const val SYNC_INTERVAL_HOURS = 6 // Sync every 6 hours
    private const val MAX_BATCH_SIZE = 10 // Back to normal batch size
    private const val MESSAGES_BEFORE_PAUSE = 20 // Pause after every 20 messages
    private const val TELEGRAM_PAUSE_SECONDS = 25 // Telegram's requested pause time

    /**
     * Get the current sync mode and baseline timestamp
     */
    private fun getSyncModeAndBaseline(): Pair<String, Long> {
        val mode = try {
            Preferences.getString(Preferences.smsSyncModeKey, "ALL")
        } catch (e: Exception) {
            Log.w(TAG, "Failed to read sync mode, defaulting to ALL", e)
            "ALL"
        }

        val baseline = try {
            Preferences.getLong(Preferences.smsSyncEnabledSinceKey, 0L)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to read baseline timestamp, using 0", e)
            0L
        }

        Log.d(TAG, "Sync mode: $mode, baseline: $baseline")
        
        // Add detailed logging for NEW_ONLY mode
        if (mode == "NEW_ONLY") {
            if (baseline > 0L) {
                val baselineDate = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date(baseline))
                Log.i(TAG, "✅ NEW_ONLY mode properly configured - baseline: $baseline ($baselineDate)")
            } else {
                Log.w(TAG, "⚠️ NEW_ONLY mode selected but baseline is 0! This will prevent proper filtering.")
            }
        } else {
            Log.d(TAG, "📦 ALL mode - will sync all unsynced messages regardless of baseline")
        }
        
        return Pair(mode, baseline)
    }

    /**
     * Perform a full synchronization of SMS messages
     */
    fun performFullSync(context: Context): Flow<SmsSyncProgress> = flow {
        Log.i(TAG, "=== STARTING FULL SMS SYNC ===")

        try {
            // Respect user preference; if disabled, stop
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            Log.d(TAG, "Preference isSmsSyncEnabledKey = $isEnabled")
            if (!isEnabled) {
                Log.w(TAG, "SMS sync disabled by user; aborting full sync")
                emit(SmsSyncProgress(0, 0, isComplete = true))
                return@flow
            }

            emit(SmsSyncProgress(currentBatch = 0, totalMessagesSynced = 0, isComplete = false))

            val channelId = getConfiguredChannelId()
            Log.d(TAG, "Configured channelId = $channelId")
            if (channelId == null) {
                Log.w(TAG, "No channel ID configured for SMS sync")
                emit(SmsSyncProgress(
                    currentBatch = 0,
                    totalMessagesSynced = 0,
                    isComplete = true,
                    errorMessage = "No Telegram channel configured"
                ))
                return@flow
            }

            Log.i(TAG, "Syncing SMS messages to channel ID: $channelId")

            // Check if we need to perform sync
            val shouldSync = shouldPerformSync()
            Log.d(TAG, "shouldPerformSync() -> $shouldSync")
            if (!shouldSync) {
                Log.i(TAG, "Sync not needed (recent sync found)")
                emit(SmsSyncProgress(
                    currentBatch = 0,
                    totalMessagesSynced = 0,
                    isComplete = true
                ))
                return@flow
            }

            // Get sync mode and baseline
            val (syncMode, baseline) = getSyncModeAndBaseline()

            // For NEW_ONLY mode, don't sync all existing messages to database
            val newLocalMessages = if (syncMode == "NEW_ONLY" && baseline > 0L) {
                Log.i(TAG, "NEW_ONLY mode: skipping full database sync, using incremental sync only")
                SmsReaderService.syncNewSmsToDatabase(context)
            } else {
                Log.i(TAG, "ALL mode: performing full database sync")
                SmsReaderService.syncAllSmsToDatabase(context)
            }
            Log.i(TAG, "Synced $newLocalMessages new SMS messages to local database")

            // Get unsynced messages from database, respecting baseline for NEW_ONLY mode
            val dao = DbHolder.database.smsMessageDao()
            val unsyncedMessages = if (syncMode == "NEW_ONLY" && baseline > 0L) {
                Log.i(TAG, "NEW_ONLY mode: getting unsynced messages after baseline $baseline")
                val messages = dao.getUnsyncedMessagesAfterBaseline(baseline, limit = 100)
                Log.d(TAG, "NEW_ONLY query returned ${messages.size} messages after baseline filter")
                if (messages.isNotEmpty()) {
                    val oldestDate = messages.minByOrNull { it.date }?.date ?: 0L
                    val newestDate = messages.maxByOrNull { it.date }?.date ?: 0L
                    Log.d(TAG, "Message date range: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date(oldestDate))} to ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date(newestDate))}")
                }
                messages
            } else {
                Log.i(TAG, "ALL mode: getting all unsynced messages")
                val messages = dao.getUnsyncedMessages(limit = 100)
                Log.d(TAG, "ALL mode query returned ${messages.size} total unsynced messages")
                messages
            }

            if (unsyncedMessages.isEmpty()) {
                Log.i(TAG, "No unsynced SMS messages found")
                emit(SmsSyncProgress(
                    currentBatch = 0,
                    totalMessagesSynced = 0,
                    isComplete = true
                ))
                updateLastSyncTimestamp()
                return@flow
            }

            Log.i(TAG, "Found ${unsyncedMessages.size} unsynced SMS messages")

            // Sync messages in batches
            var totalSynced = 0
            var currentBatch = 0
            var globalMessageCount = 0 // Track messages across all batches for 20-message pause

            unsyncedMessages.chunked(MAX_BATCH_SIZE).forEachIndexed { index, batch ->
                currentBatch++
                Log.i(TAG, "📦 Processing batch $currentBatch with ${batch.size} messages (oldest first)")
                Log.d(TAG, "📅 Batch date range: ${batch.firstOrNull()?.date} to ${batch.lastOrNull()?.date}")

                val (batchSynced, newGlobalCount) = syncMessagesBatch(batch, channelId, globalMessageCount)
                totalSynced += batchSynced
                globalMessageCount = newGlobalCount
                Log.d(TAG, "Batch $currentBatch synced $batchSynced, globalCount now $globalMessageCount")

                emit(SmsSyncProgress(
                    currentBatch = currentBatch,
                    totalMessagesSynced = totalSynced,
                    isComplete = false
                ))

                // Small delay between batches (main pause logic is in message loop)
                val totalBatches = (unsyncedMessages.size + MAX_BATCH_SIZE - 1) / MAX_BATCH_SIZE
                if (index < totalBatches - 1) {
                    Log.d(TAG, "⏳ Waiting 2 seconds before next batch")
                    delay(2000) // 2 second delay between batches
                }
            }

            // Update last sync timestamp
            updateLastSyncTimestamp()
            Log.d(TAG, "Updated last sync timestamp to now")

            Log.i(TAG, "=== FULL SMS SYNC COMPLETE: $totalSynced messages synced ===")
            emit(SmsSyncProgress(
                currentBatch = currentBatch,
                totalMessagesSynced = totalSynced,
                isComplete = true
            ))

        } catch (e: Exception) {
            Log.e(TAG, "Exception during full SMS sync", e)
            emit(SmsSyncProgress(
                currentBatch = 0,
                totalMessagesSynced = 0,
                isComplete = true,
                errorMessage = "Sync failed: ${e.message}"
            ))
        }
    }

    /**
     * Perform a quick sync to check for recent SMS messages
     */
    suspend fun performQuickSync(context: Context): SmsSyncResult {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Performing quick SMS sync")

                // Respect user preference; if disabled, skip
                val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
                Log.d(TAG, "Preference isSmsSyncEnabledKey = $isEnabled")
                if (!isEnabled) {
                    Log.w(TAG, "SMS sync disabled by user; skipping quick sync")
                    return@withContext SmsSyncResult.Success(0)
                }

                val channelId = getConfiguredChannelId()
                Log.d(TAG, "Configured channelId = $channelId")
                if (channelId == null) {
                    Log.w(TAG, "No channel ID configured for quick sync")
                    return@withContext SmsSyncResult.NoChannelConfigured
                }

                // Sync new SMS messages to local database
                val newLocalMessages = SmsReaderService.syncNewSmsToDatabase(context)
                Log.d(TAG, "syncNewSmsToDatabase returned $newLocalMessages new messages")

                // Even if nothing new was just inserted (race/timing), proceed to check unsynced messages
                Log.d(TAG, "Proceeding to unsynced fetch; newLocalMessages=$newLocalMessages")

                // Get recently unsynced messages, respecting baseline for NEW_ONLY mode
                val dao = DbHolder.database.smsMessageDao()
                val (syncMode, baseline) = getSyncModeAndBaseline()
                val unsyncedMessages = if (syncMode == "NEW_ONLY" && baseline > 0L) {
                    Log.d(TAG, "NEW_ONLY mode: getting unsynced messages after baseline $baseline")
                    val messages = dao.getUnsyncedMessagesAfterBaseline(baseline, limit = MAX_BATCH_SIZE)
                    Log.d(TAG, "NEW_ONLY quick sync query returned ${messages.size} messages")
                    messages
                } else {
                    Log.d(TAG, "ALL mode: getting all unsynced messages")
                    val messages = dao.getUnsyncedMessages(limit = MAX_BATCH_SIZE)
                    Log.d(TAG, "ALL mode quick sync query returned ${messages.size} messages")
                    messages
                }
                Log.d(TAG, "Retrieved ${unsyncedMessages.size} unsynced messages (mode: $syncMode, baseline: $baseline)")

                if (unsyncedMessages.isEmpty()) {
                    Log.d(TAG, "No unsynced messages to sync")
                    return@withContext SmsSyncResult.Success(0)
                }

                // Sync the messages
                val (syncedCount, _) = syncMessagesBatch(unsyncedMessages, channelId, 0)

                Log.d(TAG, "Quick sync complete: $syncedCount messages synced")
                SmsSyncResult.Success(syncedCount)

            } catch (e: Exception) {
                Log.e(TAG, "Exception during quick SMS sync", e)
                SmsSyncResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Sync a batch of SMS messages to Telegram with 1-second delays between messages
     * Messages are processed in chronological order (oldest first)
     * Returns Pair(syncedCount, newGlobalMessageCount)
     */
    private suspend fun syncMessagesBatch(messages: List<SmsMessage>, channelId: Long, globalMessageCount: Int): Pair<Int, Int> {
        var syncedCount = 0
        val dao = DbHolder.database.smsMessageDao()
        val remoteSmsDao = DbHolder.database.remoteSmsMessageDao()
        val failedMessageIds = mutableListOf<String>()
        var lastError: String? = null
        var currentGlobalCount = globalMessageCount // Use the passed global count

        // Buffer updates to reduce UI invalidations
        val toMarkSynced = mutableListOf<Pair<String, String>>() // (id, remoteId)
        val toInsertRemote = mutableListOf<RemoteSmsMessage>()

        for ((index, message) in messages.withIndex()) {
            try {
                if (currentGlobalCount > 0 && currentGlobalCount % MESSAGES_BEFORE_PAUSE == 0) {
                    Log.w(TAG, "🛑 Sent ${MESSAGES_BEFORE_PAUSE} messages (total: $currentGlobalCount), pausing for ${TELEGRAM_PAUSE_SECONDS} seconds as per Telegram's requirement")
                    delay(TELEGRAM_PAUSE_SECONDS * 1000L)
                    Log.i(TAG, "▶️ Resuming message sending after pause")
                }

                if (index > 0) {
                    Log.d(TAG, "⏳ Waiting 1 second before sending next message (${index + 1}/${messages.size})")
                    delay(1150)
                }

                val telegramMessage = message.formatForTelegram()
                Log.d(TAG, "📤 Sending SMS message ${index + 1}/${messages.size}: ${message.id} (${message.date})")
                val (response, exception) = BotApi.sendSmsMessage(telegramMessage, channelId)

                if (exception == null && response?.isSuccess == true) {
                    val telegramMessageId = response.get()?.messageId?.toString()
                    if (telegramMessageId != null) {
                        toMarkSynced.add(message.id to telegramMessageId)
                        toInsertRemote.add(
                            RemoteSmsMessage(
                                remoteId = telegramMessageId,
                                originalSmsId = message.id,
                                address = message.address,
                                body = message.body,
                                date = message.date,
                                type = message.type,
                                syncedAt = System.currentTimeMillis(),
                                telegramChannelId = channelId,
                                telegramMessageId = response.get()?.messageId?.toInt()
                            )
                        )
                        syncedCount++
                        currentGlobalCount++
                        Log.i(TAG, "✅ Successfully synced SMS message: ${message.id} (${currentGlobalCount % MESSAGES_BEFORE_PAUSE}/${MESSAGES_BEFORE_PAUSE} in current burst, total: $currentGlobalCount)")
                    } else {
                        Log.w(TAG, "⚠️ No message ID returned from Telegram for SMS: ${message.id}")
                        failedMessageIds.add(message.id)
                        lastError = "No message ID returned from Telegram"
                    }
                } else {
                    val errorMsg = when {
                        exception != null -> categorizeError(exception)
                        response?.isSuccess == false -> {
                            val telegramError = response.toString()
                            Log.e(TAG, "🔍 Telegram API error details: $telegramError")
                            "Telegram API returned error: ${response.javaClass.simpleName}"
                        }
                        else -> "Unknown sync error"
                    }
                    Log.e(TAG, "❌ Failed to sync SMS message ${message.id}: $errorMsg")
                    failedMessageIds.add(message.id)
                    lastError = errorMsg
                    if (errorMsg.contains("rate limit", ignoreCase = true) ||
                        errorMsg.contains("429", ignoreCase = true) ||
                        errorMsg.contains("too many requests", ignoreCase = true)) {
                        Log.w(TAG, "🚫 Telegram rate limit detected, stopping entire sync process")
                        if (failedMessageIds.isNotEmpty()) {
                            dao.batchUpdateSyncAttempts(failedMessageIds, error = lastError)
                            Log.d(TAG, "📊 Batch updated ${failedMessageIds.size} failed messages")
                        }
                        Log.w(TAG, "📊 Sync stats: $syncedCount synced, ${failedMessageIds.size} failed")
                        return Pair(syncedCount, currentGlobalCount)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception syncing SMS message ${message.id}", e)
                failedMessageIds.add(message.id)
                lastError = e.message
            }
        }

        // Apply buffered DB changes in a single transaction to minimize list invalidations
        // Use suspending transaction; DAO methods are suspend
        DbHolder.database.withTransaction {
            toMarkSynced.forEach { (id, remoteId) ->
                dao.markAsSynced(id, remoteId)
            }
            toInsertRemote.forEach { remote ->
                remoteSmsDao.insert(remote)
            }
            if (failedMessageIds.isNotEmpty()) {
                dao.batchUpdateSyncAttempts(failedMessageIds, error = lastError)
            }
        }

        Log.d(TAG, "📦 Applied DB updates in batch: synced=${toMarkSynced.size}, remoteInserted=${toInsertRemote.size}, failed=${failedMessageIds.size}")
        return Pair(syncedCount, currentGlobalCount)
    }

    /**
     * Check if sync should be performed based on last sync timestamp
     */
    private fun shouldPerformSync(): Boolean {
        val lastSyncTimestamp = Preferences.getLong(LAST_SYNC_TIMESTAMP_KEY, 0L)
        val currentTime = System.currentTimeMillis()
        val timeSinceLastSync = currentTime - lastSyncTimestamp
        val syncIntervalMs = SYNC_INTERVAL_HOURS * 60 * 60 * 1000L

        return timeSinceLastSync >= syncIntervalMs
    }

    /**
     * Update the last sync timestamp
     */
    private fun updateLastSyncTimestamp() {
        Preferences.edit {
            putLong(LAST_SYNC_TIMESTAMP_KEY, System.currentTimeMillis())
        }
    }

    /**
     * Get configured channel ID from preferences
     */
    private fun getConfiguredChannelId(): Long? {
        val channelId = Preferences.getEncryptedLong(Preferences.channelId, 0L)
        return if (channelId != 0L) channelId else null
    }

    /**
     * Categorize error messages for better user understanding
     */
    private fun categorizeError(exception: Exception?): String {
        val errorMessage = exception?.message?.lowercase() ?: "unknown error"

        return when {
            errorMessage.contains("429") || errorMessage.contains("too many requests") ->
                "Rate limit exceeded - too many messages sent"

            errorMessage.contains("network") || errorMessage.contains("timeout") ->
                "Network connection error - check internet"

            errorMessage.contains("unauthorized") || errorMessage.contains("401") ->
                "Bot token invalid - check Telegram settings"

            errorMessage.contains("forbidden") || errorMessage.contains("403") ->
                "Bot not authorized for channel - check permissions"

            errorMessage.contains("bad request") || errorMessage.contains("400") ->
                "Invalid message format or content"

            errorMessage.contains("not found") || errorMessage.contains("404") ->
                "Channel not found - check channel ID"

            else -> "Telegram API error: ${exception?.message ?: "Unknown"}"
        }
    }

    /**
     * Force a sync regardless of timestamp (for manual sync)
     */
    fun forceSync(context: Context): Flow<SmsSyncProgress> = flow {
        Log.i(TAG, "Force SMS sync requested")
        // Reset last sync timestamp to force sync
        Preferences.edit {
            putLong(LAST_SYNC_TIMESTAMP_KEY, 0L)
        }

        // Perform full sync
        performFullSync(context).collect { progress ->
            emit(progress)
        }
    }
}

/**
 * Progress data for SMS sync operations
 */
data class SmsSyncProgress(
    val currentBatch: Int,
    val totalMessagesSynced: Int,
    val isComplete: Boolean,
    val errorMessage: String? = null
)

/**
 * Result of SMS sync operations
 */
sealed class SmsSyncResult {
    data class Success(val messagesSynced: Int) : SmsSyncResult()
    data class Error(val message: String) : SmsSyncResult()
    object NoChannelConfigured : SmsSyncResult()
}
