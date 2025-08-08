package com.akslabs.chitralaya.services

import android.content.Context
import android.util.Log
import com.akslabs.SandeshVahak.api.BotApi
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

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
     * Perform a full synchronization of SMS messages
     */
    fun performFullSync(context: Context): Flow<SmsSyncProgress> = flow {
        Log.i(TAG, "=== STARTING FULL SMS SYNC ===")

        try {
            // Respect user preference; if disabled, stop
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (!isEnabled) {
                Log.w(TAG, "SMS sync disabled by user; aborting full sync")
                emit(SmsSyncProgress(0, 0, isComplete = true))
                return@flow
            }

            emit(SmsSyncProgress(currentBatch = 0, totalMessagesSynced = 0, isComplete = false))

            val channelId = getConfiguredChannelId()
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
            if (!shouldPerformSync()) {
                Log.i(TAG, "Sync not needed (recent sync found)")
                emit(SmsSyncProgress(
                    currentBatch = 0,
                    totalMessagesSynced = 0,
                    isComplete = true
                ))
                return@flow
            }
            
            // First, sync all SMS from device to local database
            val newLocalMessages = SmsReaderService.syncAllSmsToDatabase(context)
            Log.i(TAG, "Synced $newLocalMessages new SMS messages to local database")
            
            // Get unsynced messages from database
            val dao = DbHolder.database.smsMessageDao()
            val unsyncedMessages = dao.getUnsyncedMessages(limit = 100)
            
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
                if (!isEnabled) {
                    Log.w(TAG, "SMS sync disabled by user; skipping quick sync")
                    return@withContext SmsSyncResult.Success(0)
                }

                val channelId = getConfiguredChannelId()
                if (channelId == null) {
                    Log.w(TAG, "No channel ID configured for quick sync")
                    return@withContext SmsSyncResult.NoChannelConfigured
                }

                // Sync new SMS messages to local database
                val newLocalMessages = SmsReaderService.syncNewSmsToDatabase(context)

                if (newLocalMessages == 0) {
                    Log.d(TAG, "No new SMS messages found")
                    return@withContext SmsSyncResult.Success(0)
                }

                // Get recently unsynced messages
                val dao = DbHolder.database.smsMessageDao()
                val unsyncedMessages = dao.getUnsyncedMessages(limit = MAX_BATCH_SIZE)

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

        for ((index, message) in messages.withIndex()) {
            try {
                // Check if we need to pause BEFORE sending (after every 20 successful messages)
                if (currentGlobalCount > 0 && currentGlobalCount % MESSAGES_BEFORE_PAUSE == 0) {
                    Log.w(TAG, "🛑 Sent ${MESSAGES_BEFORE_PAUSE} messages (total: $currentGlobalCount), pausing for ${TELEGRAM_PAUSE_SECONDS} seconds as per Telegram's requirement")
                    delay(TELEGRAM_PAUSE_SECONDS * 1000L) // 25 second pause
                    Log.i(TAG, "▶️ Resuming message sending after pause")
                }

                // Add 1-second delay between messages
                if (index > 0) {
                    Log.d(TAG, "⏳ Waiting 1 second before sending next message (${index + 1}/${messages.size})")
                    delay(1150) // 1.15 second delay as requested
                }

                // Format message for Telegram
                val telegramMessage = message.formatForTelegram()

                Log.d(TAG, "📤 Sending SMS message ${index + 1}/${messages.size}: ${message.id} (${message.date})")

                // Send to Telegram with enhanced error handling
                val (response, exception) = BotApi.sendSmsMessage(telegramMessage, channelId)

                if (exception == null && response?.isSuccess == true) {
                    val telegramMessageId = response.get()?.messageId?.toString()

                    if (telegramMessageId != null) {
                        // Mark as synced in local database
                        dao.markAsSynced(message.id, telegramMessageId)

                        // Add to remote SMS database
                        val remoteSms = RemoteSmsMessage(
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
                        remoteSmsDao.insert(remoteSms)

                        syncedCount++
                        currentGlobalCount++ // Count successful sends globally
                        Log.i(TAG, "✅ Successfully synced SMS message: ${message.id} (${currentGlobalCount % MESSAGES_BEFORE_PAUSE}/${MESSAGES_BEFORE_PAUSE} in current burst, total: $currentGlobalCount)")
                    } else {
                        Log.w(TAG, "⚠️ No message ID returned from Telegram for SMS: ${message.id}")
                        failedMessageIds.add(message.id)
                        lastError = "No message ID returned from Telegram"
                    }
                } else {
                    // Enhanced error handling with specific error types
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

                    // If it's a rate limit error, stop the entire sync to prevent further failures
                    if (errorMsg.contains("rate limit", ignoreCase = true) ||
                        errorMsg.contains("429", ignoreCase = true) ||
                        errorMsg.contains("too many requests", ignoreCase = true)) {
                        Log.w(TAG, "🚫 Telegram rate limit detected, stopping entire sync process")

                        // Batch update all failed messages to reduce UI updates
                        if (failedMessageIds.isNotEmpty()) {
                            dao.batchUpdateSyncAttempts(failedMessageIds, error = lastError)
                            Log.d(TAG, "📊 Batch updated ${failedMessageIds.size} failed messages")
                        }

                        Log.w(TAG, "📊 Sync stats: $syncedCount synced, ${failedMessageIds.size} failed")

                        // Return early to stop processing more messages
                        return Pair(syncedCount, currentGlobalCount)
                    }
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "Exception syncing SMS message ${message.id}", e)
                failedMessageIds.add(message.id)
                lastError = e.message
            }
        }

        // Batch update any remaining failed messages to reduce UI flashing
        if (failedMessageIds.isNotEmpty()) {
            dao.batchUpdateSyncAttempts(failedMessageIds, error = lastError)
            Log.d(TAG, "📊 Batch updated ${failedMessageIds.size} failed messages at end of sync")
        }

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
