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
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (isEnabled) {
                Log.d(TAG, "KeepAlive: ensuring SmsObserverService is running")
                val serviceIntent = Intent(applicationContext, SmsObserverService::class.java)
                applicationContext.startForegroundService(serviceIntent)
            } else {
                Log.d(TAG, "KeepAlive: SMS sync disabled; not starting service")
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


