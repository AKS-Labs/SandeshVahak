package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService

class KeepAliveWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork() CALLED")
        try {
            // Initialize preferences to ensure they're available
            try {
                Preferences.init(applicationContext)
                Log.d(TAG, "Preferences initialized successfully.")
            } catch (e: Exception) {
                // Log and continue, as preference check below will use defaults if init failed
                Log.w(TAG, "Preferences.init failed in KeepAliveWorker, will use defaults for check.", e)
            }
            
            val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            Log.d(TAG, "Current SMS Sync enabled state: $isSyncEnabled")

            if (isSyncEnabled) {
                Log.i(TAG, "SMS Sync is enabled. Attempting to ensure SmsObserverService is (re)started.")
                try {
                    // Call the static start method of SmsObserverService.
                    // This method handles calling startForegroundService with the correct intent action.
                    SmsObserverService.start(applicationContext)
                    Log.d(TAG, "SmsObserverService.start() called successfully from KeepAliveWorker.")
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to call SmsObserverService.start() from KeepAliveWorker.", e)
                    // If this fails, it might indicate a more significant issue with starting foreground services.
                    // Depending on the desired behavior, could return Result.retry() or Result.failure().
                    // For now, we'll let it be a success as the worker's attempt was made.
                }
            } else {
                Log.i(TAG, "SMS Sync is disabled. KeepAliveWorker will not attempt to start SmsObserverService.")
                // SmsObserverService itself should stop if preferences indicate sync is off.
            }
            
            // The responsibility of enqueueing specific sync tasks (like periodic full sync or catch-up)
            // now lies with SmsObserverService based on its state and preference changes.
            // KeepAliveWorker's role is just to poke the service.

            Log.d(TAG, "doWork() COMPLETED successfully.")
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error in KeepAliveWorker.doWork()", e)
            // Retry if an unexpected error occurs during the worker's execution.
            return Result.retry()
        }
    }

    companion object {
        // Using a more specific tag for easier log filtering
        private const val TAG = "KeepAliveWorker_DEBUG"
    }
}
