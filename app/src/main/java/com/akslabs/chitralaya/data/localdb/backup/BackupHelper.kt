package com.akslabs.Suchak.data.localdb.backup

import android.content.Context
import android.net.Uri
import android.util.Log
import com.akslabs.Suchak.R
import com.akslabs.Suchak.api.BotApi
import com.akslabs.Suchak.data.localdb.DbHolder
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.Suchak.utils.toastFromMainThread
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object BackupHelper {
    const val JSON_MIME = "application/json"
    private const val TAG = "BackupHelper"
    private const val DATABASE_BACKUP_PREFIX = "database"

    private val mapper by lazy {
        ObjectMapper().apply {
            // Configure to ignore unknown fields during deserialization
            // This makes import robust against schema changes
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        }
    }

    suspend fun exportDatabase(uri: Uri, context: Context) {
        try {
            val smsMessages = DbHolder.database.smsMessageDao().getAll()
            val remoteSmsMessages = DbHolder.database.remoteSmsMessageDao().getAll()

            val backupFile = BackupFile(
                smsMessages = smsMessages,
                remoteSmsMessages = remoteSmsMessages
            )

            Log.i(TAG, "Creating backup: ${smsMessages.size} SMS, ${remoteSmsMessages.size} remote SMS")

            context.contentResolver.openOutputStream(uri)?.use {
                val backupJson = mapper.writeValueAsBytes(backupFile)
                it.write(backupJson)
            }
            context.toastFromMainThread("Export successful: ${smsMessages.size} SMS messages")
        } catch (e: Exception) {
            context.toastFromMainThread(e.localizedMessage)
            Log.e(TAG, "Export failed", e)
        }
    }

    suspend fun importDatabase(uri: Uri, context: Context) {
        try {
            context.contentResolver.openInputStream(uri)?.use {
                val backupFile = mapper.readValue(it.readBytes(), BackupFile::class.java)

                Log.i(TAG, "📥 Backup file parsed: ${backupFile.smsMessages.size} SMS, ${backupFile.remoteSmsMessages.size} remote SMS")

                // Import SMS data

                // Import SMS messages (new functionality)
                if (backupFile.smsMessages.isNotEmpty()) {
                    DbHolder.database.smsMessageDao().insertAll(*backupFile.smsMessages.toTypedArray())
                    Log.i(TAG, "Imported ${backupFile.smsMessages.size} SMS messages")
                }

                if (backupFile.remoteSmsMessages.isNotEmpty()) {
                    DbHolder.database.remoteSmsMessageDao().insertAll(*backupFile.remoteSmsMessages.toTypedArray())
                    Log.i(TAG, "Imported ${backupFile.remoteSmsMessages.size} remote SMS messages")
                }

                val totalSms = backupFile.smsMessages.size + backupFile.remoteSmsMessages.size
                context.toastFromMainThread("Import successful: $totalSms SMS messages")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Import failed", e)
            context.toastFromMainThread(e.localizedMessage)
        }
    }

    /**
     * Upload database backup to Telegram
     */
    suspend fun uploadDatabaseToTelegram(context: Context): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                Log.i(TAG, "=== UPLOADING DATABASE TO TELEGRAM ===")

                // Get channel ID
                val channelId = Preferences.getEncryptedLong(Preferences.channelId, 0L)
                if (channelId == 0L) {
                    return@withContext Result.failure(Exception("No Telegram channel configured"))
                }

                // Create database backup
                val smsMessages = DbHolder.database.smsMessageDao().getAll()
                val remoteSmsMessages = DbHolder.database.remoteSmsMessageDao().getAll()

                val backupFile = BackupFile(
                    smsMessages = smsMessages,
                    remoteSmsMessages = remoteSmsMessages
                )

                Log.i(TAG, "Database backup created: ${smsMessages.size} SMS, ${remoteSmsMessages.size} remote SMS")

                // Convert to JSON
                val backupJson = mapper.writeValueAsBytes(backupFile)

                // Create filename with date
                val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault())
                val timestamp = dateFormat.format(Date())
                val fileName = "${DATABASE_BACKUP_PREFIX}_${timestamp}.json"

                // Create temporary file
                val tempFile = File.createTempFile("database_backup", ".json")
                tempFile.writeBytes(backupJson)

                Log.i(TAG, "Created backup file: $fileName (${tempFile.length()} bytes)")

                // Upload to Telegram
                Log.i(TAG, "Uploading to Telegram channel: $channelId")
                val uploadResult = BotApi.sendFile(tempFile, channelId)

                val (response, error) = uploadResult
                if (error != null) {
                    Log.e(TAG, "❌ Failed to upload database backup", error)
                    tempFile.delete()
                    Result.failure(error)
                } else {
                    val document = response?.body()?.result?.document
                    if (document != null) {
                        Log.i(TAG, "✅ Database backup uploaded successfully: ${document.fileId}")

                        // Store backup info
                        Preferences.edit {
                            putString("last_database_backup_file_id", document.fileId)
                            putString("last_database_backup_filename", fileName)
                            putLong("last_database_backup_timestamp", System.currentTimeMillis())
                            putLong("last_database_backup_sms", smsMessages.size.toLong())
                            putLong("last_database_backup_remote_sms", remoteSmsMessages.size.toLong())
                        }

                        // Clean up temp file
                        tempFile.delete()

                        Result.success("Database uploaded to Telegram: $fileName")
                    } else {
                        tempFile.delete()
                        Result.failure(Exception("Upload failed: No document in response"))
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "Exception uploading database to Telegram", e)
                Result.failure(e)
            }
        }
    }

    /**
     * Download and import database backup from Telegram
     */
    suspend fun downloadDatabaseFromTelegram(fileId: String, context: Context): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                Log.i(TAG, "=== DOWNLOADING DATABASE FROM TELEGRAM ===")
                Log.i(TAG, "Downloading backup file: $fileId")

                // Download file from Telegram
                val fileBytes = BotApi.getFile(fileId)
                if (fileBytes == null) {
                    return@withContext Result.failure(Exception("Failed to download backup file"))
                }

                // Parse JSON
                val backupFile = mapper.readValue(fileBytes, BackupFile::class.java)

                Log.i(TAG, "Database backup downloaded: ${backupFile.smsMessages.size} SMS, ${backupFile.remoteSmsMessages.size} remote SMS")

                // Import SMS data
                if (backupFile.smsMessages.isNotEmpty()) {
                    DbHolder.database.smsMessageDao().insertAll(*backupFile.smsMessages.toTypedArray())
                    Log.i(TAG, "Imported ${backupFile.smsMessages.size} SMS messages with sync status")
                }

                if (backupFile.remoteSmsMessages.isNotEmpty()) {
                    DbHolder.database.remoteSmsMessageDao().insertAll(*backupFile.remoteSmsMessages.toTypedArray())
                    Log.i(TAG, "Imported ${backupFile.remoteSmsMessages.size} remote SMS messages with Telegram IDs")
                }

                Log.i(TAG, "✅ Database imported successfully")

                // Update preferences
                Preferences.edit {
                    putLong("last_database_import_timestamp", System.currentTimeMillis())
                    putLong("last_database_import_sms", backupFile.smsMessages.size.toLong())
                    putLong("last_database_import_remote_sms", backupFile.remoteSmsMessages.size.toLong())
                }

                val totalSms = backupFile.smsMessages.size + backupFile.remoteSmsMessages.size
                Result.success("Database imported: $totalSms SMS messages")

            } catch (e: Exception) {
                Log.e(TAG, "Exception downloading database from Telegram", e)
                Result.failure(e)
            }
        }
    }

    /**
     * Check if database backup is up to date
     */
    suspend fun isDatabaseBackupUpToDate(): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val lastBackupTimestamp = Preferences.getLong("last_database_backup_timestamp", 0L)
                val lastBackupSms = Preferences.getLong("last_database_backup_sms", 0L).toInt()
                val lastBackupRemoteSms = Preferences.getLong("last_database_backup_remote_sms", 0L).toInt()

                // Get current database state
                val currentSms = DbHolder.database.smsMessageDao().getAll().size
                val currentRemoteSms = DbHolder.database.remoteSmsMessageDao().getAll().size

                // Check if backup exists and data hasn't changed
                val hasBackup = lastBackupTimestamp > 0
                val dataUnchanged = (currentSms == lastBackupSms && currentRemoteSms == lastBackupRemoteSms)

                Log.d(TAG, "Backup status - Has backup: $hasBackup, Data unchanged: $dataUnchanged")
                Log.d(TAG, "Current: $currentSms SMS, $currentRemoteSms remote | Last backup: $lastBackupSms SMS, $lastBackupRemoteSms remote")

                hasBackup && dataUnchanged

            } catch (e: Exception) {
                Log.e(TAG, "Error checking backup status", e)
                false
            }
        }
    }

    /**
     * Check if daily backup is needed
     */
    fun shouldCreateDailyBackup(): Boolean {
        val lastBackup = Preferences.getLong("last_database_backup_timestamp", 0L)
        val oneDayAgo = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        return lastBackup < oneDayAgo
    }

    /**
     * Get backup statistics for UI display
     */
    suspend fun getBackupStats(): BackupStats {
        return withContext(Dispatchers.IO) {
            val lastBackupTime = Preferences.getLong("last_database_backup_timestamp", 0L)
            val lastBackupFilename = Preferences.getString("last_database_backup_filename", "")
            val lastImportTime = Preferences.getLong("last_database_import_timestamp", 0L)

            val currentSmsMessages = DbHolder.database.smsMessageDao().getAll().size
            val currentRemoteSmsMessages = DbHolder.database.remoteSmsMessageDao().getAll().size
            val isUpToDate = isDatabaseBackupUpToDate()

            BackupStats(
                lastBackupTime = lastBackupTime,
                lastBackupFilename = lastBackupFilename,
                lastImportTime = lastImportTime,
                currentSmsMessages = currentSmsMessages,
                currentRemoteSmsMessages = currentRemoteSmsMessages,
                isUpToDate = isUpToDate,
                needsDailyBackup = shouldCreateDailyBackup()
            )
        }
    }

    data class BackupStats(
        val lastBackupTime: Long,
        val lastBackupFilename: String,
        val lastImportTime: Long,
        val currentSmsMessages: Int,
        val currentRemoteSmsMessages: Int,
        val isUpToDate: Boolean,
        val needsDailyBackup: Boolean
    )
}