package com.akslabs.chitralaya.workers

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.chitralaya.R
import com.akslabs.chitralaya.data.localdb.Preferences // Corrected import
import com.akslabs.chitralaya.services.SmsContentObserver
import com.akslabs.chitralaya.services.SmsSyncService
import com.akslabs.chitralaya.services.SmsSyncResult
import com.akslabs.chitralaya.utils.NotificationHelper // Assuming NotificationHelper is in this package

class StartupSyncWorker(
    private val context: Context, // Changed to val
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        private const val TAG = "StartupSyncWorker"
        private const val STARTUP_NOTIFICATION_ID = 3001
        private const val STARTUP_NOTIFICATION_CHANNEL_ID = "StartupSyncChannel"
    }

    override suspend fun doWork(): Result {
        Log.i(TAG, "StartupSyncWorker started.")

        try {
            // Ensure preferences are initialized (belt and braces)
            Preferences.init(applicationContext)

            val isSmsSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (!isSmsSyncEnabled) {
                Log.i(TAG, "SMS Sync is disabled. StartupSyncWorker finishing.")
                return Result.success()
            }

            Log.i(TAG, "SMS Sync is enabled. Proceeding with startup tasks.")

            // 1. Perform a catch-up sync.
            // Using performQuickSync as it's meant for recent messages.
            // If a more thorough sync is needed, SmsSyncService.performFullSync(applicationContext) could be used.
            Log.i(TAG, "Attempting catch-up sync using performQuickSync.")
            when (val quickSyncResult = SmsSyncService.performQuickSync(applicationContext)) {
                is SmsSyncResult.Success -> Log.i(TAG, "Startup catch-up sync successful: ${quickSyncResult.messagesSynced} messages.")
                is SmsSyncResult.Error -> Log.w(TAG, "Startup catch-up sync failed: ${quickSyncResult.message}")
                is SmsSyncResult.NoChannelConfigured -> Log.i(TAG, "Startup catch-up: No channel configured.")
            }

            // 2. Ensure SmsContentObserver is active.
            // Re-registering the observer should be safe.
            // It typically checks if an instance already exists.
            Log.i(TAG, "Ensuring SmsContentObserver is started.")
            SmsContentObserver.startObserving(applicationContext)

            // 3. Notification for user if needed (e.g., on restrictive OS versions)
            // This is a placeholder. Logic to determine if a notification is truly needed
            // would depend on specific checks (e.g., background restrictions, missing permissions post-reboot).
            // For now, we are not adding a notification here to avoid being too intrusive,
            // unless specific conditions that break background work are detected.
            // If READ_SMS is missing, SmsContentObserver.startObserving might log an error.
            // If POST_NOTIFICATIONS is missing for Android 13+, we can't show this notification anyway.

            Log.i(TAG, "StartupSyncWorker completed successfully.")
            return Result.success()

        } catch (e: Exception) {
            Log.e(TAG, "Error in StartupSyncWorker", e)
            return Result.retry() // Retry if there was a temporary issue
        }
    }
}