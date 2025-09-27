package com.akslabs.SandeshVahak.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsSyncResult // Assuming SmsSyncResult is in this package
import com.akslabs.SandeshVahak.services.SmsSyncService // Assuming SmsSyncService is in this package
import com.akslabs.SandeshVahak.ui.MainActivity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive // Import for coroutineContext.isActive
import kotlinx.coroutines.withContext

class SmsSyncWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val notificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val TAG = "SmsSyncWorker"
        const val NOTIFICATION_ID = 18698 // Different from service's ID
        const val CHANNEL_ID = "SmsSyncWorkerChannel"
        const val CHANNEL_NAME = "SMS Sync Worker Channel"

        // Input data keys
        const val KEY_SYNC_MODE = "SYNC_MODE"
        const val SYNC_MODE_FULL = "FULL"
        const val SYNC_MODE_CATCH_UP = "CATCH_UP"
    }

    override suspend fun doWork(): Result = coroutineScope {
        val syncMode = inputData.getString(KEY_SYNC_MODE) ?: Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)

        Log.i(TAG, "doWork: Starting SMS Sync work. Mode: $syncMode")

        try {
            createNotificationChannel()
            val initialNotification = createNotification("Starting SMS sync...")
            // Specify foregroundServiceType for Android 12+
            setForeground(ForegroundInfo(NOTIFICATION_ID, initialNotification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC))

            when (syncMode) {
                SYNC_MODE_FULL, Preferences.SMS_SYNC_MODE_ALL -> {
                    performFullSmsSync()
                }
                SYNC_MODE_CATCH_UP, Preferences.SMS_SYNC_MODE_NEW_ONLY -> {
                    performCatchUpSync()
                }
                else -> {
                    Log.w(TAG, "Unknown sync mode: $syncMode. Defaulting to catch-up.")
                    performCatchUpSync()
                }
            }
            Log.i(TAG, "doWork: SMS Sync work finished successfully.")
            Result.success()
        } catch (e: CancellationException) {
            Log.i(TAG, "doWork: SMS Sync work was cancelled.", e)
            updateNotification("SMS sync cancelled.")
            Result.failure() // Or Result.retry() if appropriate
        } catch (e: Exception) {
            Log.e(TAG, "doWork: Error during SMS sync work.", e)
            updateNotification("SMS sync failed: ${e.message?.take(50)}")
            Result.failure() // Or Result.retry()
        } finally {
            // Clean up foreground state if necessary, though WorkManager often handles this.
            Log.d(TAG, "doWork: Finalizing.")
        }
    }

    private suspend fun performFullSmsSync() {
        Log.i(TAG, "performFullSmsSync: Initiating full SMS sync.")
        updateNotification("Starting full SMS sync...")

        var syncError: String? = null
        var totalMessagesProcessed = 0

        try {
            SmsSyncService.forceSync(applicationContext).collect { progress ->
                if (!this@SmsSyncWorker.coroutineContext.isActive) throw CancellationException("Coroutine cancelled during fullSmsSync")
                totalMessagesProcessed = progress.totalMessagesSynced
                Log.i(TAG, "Full Sync Progress: Batch ${progress.currentBatch}, Synced $totalMessagesProcessed, Complete: ${progress.isComplete}, Error: ${progress.errorMessage}")
                if (progress.isComplete) {
                    if (progress.errorMessage != null) {
                        syncError = progress.errorMessage
                        updateNotification("Full sync failed: ${progress.errorMessage?.take(50)}")
                    } else {
                        updateNotification("Full SMS sync completed. $totalMessagesProcessed messages processed.")
                    }
                } else {
                    updateNotification("Syncing all: $totalMessagesProcessed messages so far...")
                }
            }
        } catch (e: CancellationException) {
            Log.i(TAG, "performFullSmsSync: Coroutine was cancelled.")
            updateNotification("Full sync cancelled.")
            throw e // Re-throw to be caught by doWork's handler
        } catch (e: Exception) {
            Log.e(TAG, "Exception during forceSync collection", e)
            syncError = e.message ?: "Unknown error during sync"
            updateNotification("Full sync critically failed: ${syncError?.take(50)}")
            throw e // Re-throw
        }

        if (syncError == null && this@SmsSyncWorker.coroutineContext.isActive) {
            Log.i(TAG, "performFullSmsSync: Full sync process completed successfully ($totalMessagesProcessed messages). Switching to NEW_ONLY mode.")
            Preferences.edit {
                putString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
                // Consider if syncEnabledSinceKey needs update here by worker or by calling code
            }
        } else if (syncError != null) {
            Log.e(TAG, "performFullSmsSync: Full sync encountered an error: $syncError.")
        }
        Log.i(TAG, "performFullSmsSmsSync: END")
    }

    private suspend fun performCatchUpSync() {
        Log.i(TAG, "performCatchUpSync: Starting catch-up sync.")
        updateNotification("Processing recent SMS...")

        try {
            val quickSyncResult = SmsSyncService.performQuickSync(applicationContext)
            if (!this@SmsSyncWorker.coroutineContext.isActive) throw CancellationException("Coroutine cancelled during quickSyncResult")
            when (quickSyncResult) {
                is SmsSyncResult.Success -> {
                    Log.i(TAG, "Catch-up sync: ${quickSyncResult.messagesSynced} messages.")
                    updateNotification("SMS sync active. ${quickSyncResult.messagesSynced} recent messages processed.")
                }
                is SmsSyncResult.Error -> {
                    Log.e(TAG, "Catch-up sync error: ${quickSyncResult.message}")
                    updateNotification("SMS catch-up failed: ${quickSyncResult.message.take(50)}")
                }
                is SmsSyncResult.NoChannelConfigured ->{
                     Log.w(TAG, "Catch-up: No channel configured.")
                     updateNotification("SMS sync: Setup needed.")
                }
            }
        } catch (e: CancellationException) {
            Log.i(TAG, "performCatchUpSync: Coroutine was cancelled.")
            updateNotification("SMS catch-up cancelled.")
            throw e // Re-throw
        } catch (e: Exception) {
            Log.e(TAG, "Exception during catch-up sync", e)
            updateNotification("SMS catch-up failed: ${e.message?.take(50)}")
            throw e // Re-throw
        }
        Log.i(TAG, "performCatchUpSync: END")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Channel for background SMS sync operations via WorkManager"
            }
            notificationManager.createNotificationChannel(channel)
            Log.d(TAG, "Notification channel created.")
        }
    }

    private fun createNotification(contentText: String): Notification {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, pendingIntentFlags)

        return NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("SandeshVahak SMS Sync (Worker)")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.cloud_arrow_up_solid) // Ensure this drawable exists
            .setOngoing(true)
            .setAutoCancel(false)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setContentIntent(pendingIntent)
            .setSilent(true)
            .build()
    }

    private fun updateNotification(contentText: String) {
        // Check if SMS sync is enabled. Avoid updating notification if it's disabled.
        // This check might be more complex depending on how Preferences are updated now.
        // For simplicity, we'll update it for now.
        // val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
        // if (!isSyncEnabled) {
        //    Log.d(TAG, "Not updating notification as sync is disabled: $contentText")
        //    return
        // }
        notificationManager.notify(NOTIFICATION_ID, createNotification(contentText))
        Log.d(TAG, "Notification updated with text: $contentText")
    }
}
