package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.services.SmsObserverService

class BootServiceStartWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val TAG = "BootServiceStartWorker_DEBUG"
        const val UNIQUE_WORK_NAME = "BootServiceStartWork"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "doWork CALLED - Attempting to send ACTION_START_SERVICE to SmsObserverService.")
        return try {
            // Start the service with ACTION_START_SERVICE to promote it to foreground
            SmsObserverService.start(applicationContext, SmsObserverService.ACTION_START_SERVICE)
            Log.i(TAG, "ACTION_START_SERVICE sent to SmsObserverService successfully from worker.")
            Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send ACTION_START_SERVICE to SmsObserverService from worker.", e)
            Result.failure() 
        }
    }
}