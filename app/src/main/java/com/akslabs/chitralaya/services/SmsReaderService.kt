package com.akslabs.chitralaya.services

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.akslabs.Suchak.data.localdb.DbHolder
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.utils.Operations
import com.akslabs.chitralaya.utils.PerformanceMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

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
                Log.i(TAG, "Reading SMS messages after timestamp: $timestamp")
                val messages = mutableListOf<SmsMessage>()
                
                val contentResolver = context.contentResolver
                val selection = "${Telephony.Sms.DATE} > ?"
                val selectionArgs = arrayOf(timestamp.toString())
                
                val cursor = contentResolver.query(
                    Telephony.Sms.CONTENT_URI,
                    SMS_PROJECTION,
                    selection,
                    selectionArgs,
                    "${Telephony.Sms.DATE} ASC"
                )

                cursor?.use { c ->
                    while (c.moveToNext()) {
                        val smsMessage = parseSmsFromCursor(c)
                        if (smsMessage != null) {
                            messages.add(smsMessage)
                        }
                    }
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
     */
    suspend fun syncAllSmsToDatabase(context: Context): Int {
        return PerformanceMonitor.trackSuspendOperation(Operations.SMS_SYNC_TO_DB) {
            withContext(Dispatchers.IO) {
                Log.i(TAG, "🔄 Starting optimized SMS database sync...")
                val allSmsMessages = readAllSmsMessages(context)
                val dao = DbHolder.database.smsMessageDao()

                var newCount = 0
                var updatedCount = 0
                val batchSize = 100

                // Process in batches for better performance
                allSmsMessages.chunked(batchSize).forEachIndexed { batchIndex, batch ->
                    Log.d(TAG, "Processing batch ${batchIndex + 1} with ${batch.size} messages")

                    for (smsMessage in batch) {
                        val exists = dao.exists(smsMessage.id)
                        if (exists) {
                            // Update existing message
                            val existing = dao.getById(smsMessage.id)
                            if (existing != null && !existing.isSynced) {
                                // Only update if not already synced to preserve sync status
                                dao.update(smsMessage.copy(
                                    isSynced = existing.isSynced,
                                    remoteId = existing.remoteId,
                                    syncedAt = existing.syncedAt,
                                    syncAttempts = existing.syncAttempts,
                                    lastSyncAttempt = existing.lastSyncAttempt,
                                    syncError = existing.syncError
                                ))
                                updatedCount++
                            }
                        } else {
                            // Insert new message
                            dao.insert(smsMessage)
                            newCount++
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
                
                Log.i(TAG, "Starting incremental SMS sync from timestamp: $lastMessageDate")
                val newSmsMessages = readSmsMessagesAfter(context, lastMessageDate)
                
                var insertedCount = 0
                for (smsMessage in newSmsMessages) {
                    if (!dao.exists(smsMessage.id)) {
                        dao.insert(smsMessage)
                        insertedCount++
                    }
                }
                
                Log.i(TAG, "Incremental SMS sync complete: $insertedCount new messages")
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
