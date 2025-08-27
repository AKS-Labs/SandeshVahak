package com.akslabs.chitralaya.workers

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.chitralaya.services.SmsObserverService

/**
 * Periodic lightweight worker to ensure the foreground SMS observer service is running
 * and WorkManager schedules persist across device states.
 */
class KeepAliveWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Initialize preferences to ensure they're available
            try {
                Preferences.init(applicationContext)
            } catch (e: Exception) {
                Log.w(TAG, "Preferences.init failed in KeepAliveWorker", e)
            }
            
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (isEnabled) {
                Log.d(TAG, "KeepAlive: ensuring SmsObserverService is running")
                val serviceIntent = Intent(applicationContext, SmsObserverService::class.java)
                
                // Try to start the service with foreground priority
                try {
                    applicationContext.startForegroundService(serviceIntent)
                    Log.d(TAG, "KeepAlive: SmsObserverService started successfully")
                } catch (e: Exception) {
                    Log.e(TAG, "KeepAlive: Failed to start SmsObserverService, trying fallback", e)
                    // Fallback to regular start
                    try {
                        applicationContext.startService(serviceIntent)
                        Log.d(TAG, "KeepAlive: SmsObserverService started with fallback method")
                    } catch (fallbackException: Exception) {
                        Log.e(TAG, "KeepAlive: Failed to start SmsObserverService with fallback", fallbackException)
                    }
                }
            } else {
                Log.d(TAG, "KeepAlive: SMS sync disabled; not starting service")
            }
            
            // Also ensure periodic sync is scheduled
            try {
                val mode = Preferences.getString(Preferences.smsSyncModeKey, "ALL")
                if (mode == "ALL") {
                    com.akslabs.SandeshVahak.workers.WorkModule.SmsSync.enqueue()
                }
            } catch (e: Exception) {
                Log.w(TAG, "KeepAlive: Failed to ensure periodic sync scheduling", e)
            }
            
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "KeepAlive error", e)
            Result.retry()
        }
    }

    companion object {
        private const val TAG = "KeepAliveWorker"
    }
}