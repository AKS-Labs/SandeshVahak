package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService // Added import
import com.akslabs.SandeshVahak.utils.NotificationHelper // Import NotificationHelper

class KeepAliveWorker(
    val appContext: Context, // Changed to val to access it for NotificationHelper
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) { // Pass appContext

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork() CALLED")

        // Check if SmsObserverService is already active
        if (SmsObserverService.isServiceCurrentlyActive) {
            Log.i(TAG, "SmsObserverService is already active. KeepAliveWorker will not run.")
            return Result.success()
        }

        try {
            // Initialize preferences to ensure they're available
            try {
                Preferences.init(applicationContext) // appContext is already applicationContext
                Log.d(TAG, "Preferences initialized successfully.")
            } catch (e: Exception) {
                Log.w(TAG, "Preferences.init failed in KeepAliveWorker, will use defaults for check.", e)
            }
            
            val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            Log.d(TAG, "Current SMS Sync enabled state: $isSyncEnabled")

            if (isSyncEnabled) {
                Log.i(TAG, "SMS Sync is enabled. KeepAliveWorker will ensure sync worker is scheduled.")
                // NotificationHelper.showReactivateSyncNotification(applicationContext) // Removed this line

                // Also, ensure your periodic background sync worker is scheduled.
                WorkModule.SmsSync.enqueue() // Corrected method call

            } else {
                Log.i(TAG, "SMS Sync is disabled. KeepAliveWorker will not take action.")
            }
            
            Log.d(TAG, "doWork() COMPLETED successfully.")
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error in KeepAliveWorker.doWork()", e)
            return Result.retry()
        }
    }

    companion object {
        private const val TAG = "KeepAliveWorker_DEBUG"
    }
}
