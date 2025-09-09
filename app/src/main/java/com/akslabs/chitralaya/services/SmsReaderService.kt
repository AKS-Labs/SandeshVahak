package com.akslabs.chitralaya.services

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.akslabs.chitralaya.data.localdb.DbHolder
import com.akslabs.chitralaya.data.localdb.Preferences // Corrected import
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.utils.Operations
import com.akslabs.chitralaya.utils.PerformanceMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import androidx.room.withTransaction

/**
 * Service for reading SMS messages from the device's SMS content provider
 */
object SmsReaderService {
    private const val TAG = "SmsReaderService"

    // SMS content provider columns
    private val SMS_PROJECTION = arrayOf(
        Telephony.Sms._ID,
        Telephony.Sms.THREAD_ID,
        Telephony.Sms.ADDRESS,
        Telephony.Sms.PERSON,
        Telephony.Sms.DATE,
        Telephony.Sms.DATE_SENT,
        Telephony.Sms.PROTOCOL,
        Telephony.Sms.READ,
        Telephony.Sms.STATUS,
        Telephony.Sms.TYPE,
        Telephony.Sms.REPLY_PATH_PRESENT,
        Telephony.Sms.SUBJECT,
        Telephony.Sms.BODY,
        Telephony.Sms.SERVICE_CENTER,
        Telephony.Sms.LOCKED,
        Telephony.Sms.ERROR_CODE,
        Telephony.Sms.SEEN
    )

    /**
     * Read all SMS messages from the device with optimized performance
     */
    suspend fun readAllSmsMessages(context: Context): List<SmsMessage> {
        return PerformanceMonitor.trackSuspendOperation(Operations.SMS_READ_ALL) {
            withContext(Dispatchers.IO) {
                Log.i(TAG, "🚀 Starting optimized SMS reading...")
                val messages = mutableListOf<SmsMessage>()

                val contentResolver = context.contentResolver

                // Optimized query with limited initial load for speed
                val cursor = contentResolver.query(
                    Telephony.Sms.CONTENT_URI,
                    SMS_PROJECTION,
                    null,
                    null,
                    "${Telephony.Sms.DATE} DESC LIMIT 500" // Reduced to 500 for faster initial load
                )

                cursor?.use { c ->
                    val batchSize = 50
                    var count = 0

                    while (c.moveToNext()) {
                        val smsMessage = parseSmsFromCursor(c)
                        if (smsMessage != null) {
                            messages.add(smsMessage)
                        }
                        count++

                        // Process in batches to avoid blocking
                        if (count % batchSize == 0) {
                            yield() // Allow other coroutines to run
                        }
                    }
                }

                Log.i(TAG, "✅ Successfully read ${messages.size} SMS messages")
                messages
            }
        }
    }

    /**
     * Read SMS messages after a specific timestamp
     */
    suspend fun readSmsMessagesAfter(context: Context, timestamp: Long): List<SmsMessage> {
        return withContext(Dispatchers.IO) {
            try {
                Log.i(TAG, "Reading SMS messages after timestamp: $timestamp (${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(java.util.Date(timestamp))})")
                val messages = mutableListOf<SmsMessage>()

                val contentResolver = context.contentResolver
                val selection = "${Telephony.Sms.DATE} > ?"
                val selectionArgs = arrayOf(timestamp.toString())

                Log.d(TAG, "Query: uri=${Telephony.Sms.CONTENT_URI}, selection='$selection', args=${selectionArgs.toList()}, sort=${Telephony.Sms.DATE} ASC")
                val cursor = contentResolver.query(
                    Telephony.Sms.CONTENT_URI,
                    SMS_PROJECTION,
                    selection,
                    selectionArgs,
                    "${Telephony.Sms.DATE} ASC"
                )

                Log.d(TAG, "Cursor is null? ${cursor == null}")
                cursor?.use { c ->
                    var rowCount = 0
                    while (c.moveToNext()) {
                        rowCount++
                        val smsMessage = parseSmsFromCursor(c)
                        if (smsMessage != null) {
                            messages.add(smsMessage)
                            Log.v(TAG, "Row #$rowCount -> id=${smsMessage.id}, date=${smsMessage.date}, addr=${smsMessage.address}")
                        } else {
                            Log.w(TAG, "Row #$rowCount could not be parsed, skipping")
                        }
                    }
                    Log.d(TAG, "Total rows iterated: $rowCount")
                }

                Log.i(TAG, "Read ${messages.size} new SMS messages after timestamp")
                messages
            } catch (e: Exception) {
                Log.e(TAG, "Error reading new SMS messages", e)
                emptyList()
            }
        }
    }

    /**
     * Read a specific SMS message by ID
     */
    suspend fun readSmsMessageById(context: Context, smsId: String): SmsMessage? {
        return withContext(Dispatchers.IO) {
            try {
                val contentResolver = context.contentResolver
                val selection = "${Telephony.Sms._ID} = ?"
                val selectionArgs = arrayOf(smsId)

                val cursor = contentResolver.query(
                    Telephony.Sms.CONTENT_URI,
                    SMS_PROJECTION,
                    selection,
                    selectionArgs,
                    null
                )

                cursor?.use { c ->
                    if (c.moveToFirst()) {
                        return@withContext parseSmsFromCursor(c)
                    }
                }
                null
            } catch (e: Exception) {
                Log.e(TAG, "Error reading SMS message by ID: $smsId", e)
                null
            }
        }
    }

    /**
     * Sync all SMS messages to local database with optimized batch processing
     * Respects NEW_ONLY mode baseline when specified
     */
    suspend fun syncAllSmsToDatabase(context: Context): Int {
        return PerformanceMonitor.trackSuspendOperation(Operations.SMS_SYNC_TO_DB) {
            withContext(Dispatchers.IO) {
                Log.i(TAG, "🔄 Starting optimized SMS database sync...")

                // Check if we're in NEW_ONLY mode and should respect baseline
                val syncMode = try {
                    Preferences.getString(
                        Preferences.smsSyncModeKey,
                        "ALL"
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to read sync mode, defaulting to ALL", e)
                    "ALL"
                }

                val baseline = try {
                    Preferences.getLong(
                        Preferences.smsSyncEnabledSinceKey,
                        0L
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to read baseline timestamp, using 0", e)
                    0L
                }

                Log.d(TAG, "Sync mode: $syncMode, baseline: $baseline")

                val allSmsMessages = if (syncMode == "NEW_ONLY" && baseline > 0L) {
                    Log.i(TAG, "NEW_ONLY mode: filtering messages to only include those after baseline $baseline")
                    readAllSmsMessages(context).filter { message ->
                        val includeMessage = message.date >= baseline
                        if (!includeMessage) {
                            Log.v(TAG, "Filtering out message ${message.id} (date=${message.date}) as it's before baseline")
                        }
                        includeMessage
                    }
                } else {
                    Log.i(TAG, "ALL mode: including all SMS messages")
                    readAllSmsMessages(context)
                }

                Log.i(TAG, "Processing ${allSmsMessages.size} SMS messages (filtered for mode: $syncMode)")
                val dao = DbHolder.database.smsMessageDao()

                var newCount = 0
                var updatedCount = 0
                val batchSize = 100

                // Process in batches for better performance and fewer UI invalidations
                allSmsMessages.chunked(batchSize).forEachIndexed { batchIndex, batch ->
                    Log.d(TAG, "Processing batch ${batchIndex + 1} with ${batch.size} messages")

                    // Use suspending transaction to allow suspend DAO calls
                    DbHolder.database.withTransaction {
                        for (smsMessage in batch) {
                            val exists = dao.exists(smsMessage.id)
                            if (exists) {
                                // Update existing message
                                val existing = dao.getById(smsMessage.id)
                                if (existing != null && !existing.isSynced) {
                                    // Only update if not already synced to preserve sync status
                                    dao.update(
                                        smsMessage.copy(
                                            isSynced = existing.isSynced,
                                            remoteId = existing.remoteId,
                                            syncedAt = existing.syncedAt,
                                            syncAttempts = existing.syncAttempts,
                                            lastSyncAttempt = existing.lastSyncAttempt,
                                            syncError = existing.syncError
                                        )
                                    )
                                    updatedCount++
                                }
                            } else {
                                // Insert new message
                                dao.insert(smsMessage)
                                newCount++
                            }
                        }
                    }

                    // Yield after each batch to prevent blocking
                    yield()
                }

                Log.i(TAG, "✅ SMS sync complete: $newCount new, $updatedCount updated")
                newCount
            }
        }
    }

    /**
     * Sync new SMS messages to database (incremental sync)
     */
    suspend fun syncNewSmsToDatabase(context: Context): Int {
        return withContext(Dispatchers.IO) {
            try {
                val dao = DbHolder.database.smsMessageDao()
                val lastMessageDate = dao.getLatestMessageDate() ?: 0L

                // Get sync mode to determine how to handle baseline
                val syncMode = try {
                    Preferences.getString(
                        Preferences.smsSyncModeKey,
                        "ALL"
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to read sync mode, defaulting to ALL", e)
                    "ALL"
                }

                // Respect baseline when user chose NEW_ONLY mode
                val baseline = try {
                    Preferences.getLong(
                        Preferences.smsSyncEnabledSinceKey,
                        0L
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to read baseline timestamp, using 0", e)
                    0L
                }

                // For incremental sync, determine the start timestamp based on sync mode
                val bufferMs = 5000L // 5 second buffer for clock differences
                val effectiveStart = if (syncMode == "NEW_ONLY" && baseline > 0L) {
                    // User chose "sync only new messages" - always use baseline as the cutoff
                    // This ensures we only sync messages received after sync was enabled
                    Log.d(TAG, "NEW_ONLY mode: using baseline timestamp for incremental sync")
                    maxOf(0L, baseline - bufferMs)
                } else {
                    // User chose "sync all messages" or no baseline set - use incremental approach
                    Log.d(TAG, "ALL mode: using incremental sync from latest DB message")
                    lastMessageDate
                }

                Log.i(TAG, "Starting incremental SMS sync (mode: $syncMode) from timestamp: $effectiveStart (lastDB=$lastMessageDate, baseline=$baseline, buffer=$bufferMs)")
                Log.d(TAG, "Timestamp details: lastDB=${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(java.util.Date(lastMessageDate))}, baseline=${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(java.util.Date(baseline))}, effective=${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(java.util.Date(effectiveStart))}")
                
                // Add a small delay to ensure all database operations from content observer are complete
                kotlinx.coroutines.delay(100L)
                
                val newSmsMessages = readSmsMessagesAfter(context, effectiveStart)

                var insertedCount = 0
                // Batch insert to reduce UI invalidations
                val toInsert = mutableListOf<SmsMessage>()
                for (smsMessage in newSmsMessages) {
                    val exists = dao.exists(smsMessage.id)
                    Log.v(TAG, "Evaluating message id=${smsMessage.id}, date=${smsMessage.date} (${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(java.util.Date(smsMessage.date))}), exists=$exists")
                    if (!exists) {
                        toInsert.add(smsMessage)
                        insertedCount++
                        Log.i(TAG, "✅ New message queued for insert: ${smsMessage.id} from ${smsMessage.address} at ${smsMessage.date}")
                    } else {
                        Log.d(TAG, "Message ${smsMessage.id} already exists in DB, skipping")
                    }
                }
                if (toInsert.isNotEmpty()) {
                    // Use suspending transaction for suspend DAO calls
                    DbHolder.database.withTransaction {
                        dao.insertAll(*toInsert.toTypedArray())
                    }
                }

                Log.i(TAG, "Incremental SMS sync complete: $insertedCount new messages (batch)")
                insertedCount
            } catch (e: Exception) {
                Log.e(TAG, "Error in incremental SMS sync", e)
                0
            }
        }
    }

    /**
     * Parse SMS message from cursor
     */
    private fun parseSmsFromCursor(cursor: Cursor): SmsMessage? {
        return try {
            val id = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms._ID))
            val threadId = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.THREAD_ID))
            val address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)) ?: ""
            val person = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.PERSON))
            val date = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
            val dateSent = cursor.getLong(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE_SENT))
            val protocol = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.PROTOCOL))
            val read = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.READ)) == 1
            val status = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.STATUS))
            val type = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE))
            val replyPathPresent = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.REPLY_PATH_PRESENT)) == 1
            val subject = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.SUBJECT))
            val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)) ?: ""
            val serviceCenter = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.SERVICE_CENTER))
            val locked = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.LOCKED)) == 1
            val errorCode = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.ERROR_CODE))
            val seen = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.SEEN)) == 1

            SmsMessage(
                id = id,
                threadId = threadId,
                address = address,
                person = person,
                date = date,
                dateSent = if (dateSent > 0) dateSent else null,
                protocol = protocol,
                read = read,
                status = status,
                type = type,
                replyPathPresent = replyPathPresent,
                subject = subject,
                body = body,
                serviceCenter = serviceCenter,
                locked = locked,
                errorCode = errorCode,
                seen = seen
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing SMS from cursor", e)
            null
        }
    }
}
