package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.chitralaya.data.localdb.Preferences
import com.akslabs.chitralaya.workers.WorkModule // Corrected import

class BootCompletedReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "BootCompletedReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Boot completed intent received: ${intent.action}")
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.i(TAG, "Device boot completed. Initializing preferences and attempting to enqueue startup sync work.")

            try {
                Preferences.init(context.applicationContext)
                Log.d(TAG, "Preferences initialized successfully in BootCompletedReceiver")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to initialize preferences in BootCompletedReceiver", e)
            }
            
            val isSmsSyncEnabled = try {
                Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to read SMS sync enabled preference in BootCompletedReceiver, defaulting to false", e)
                false
            }

            if (isSmsSyncEnabled) {
                Log.i(TAG, "SMS Sync is enabled. Enqueueing StartupSyncWorker.")
                WorkModule.SmsSync.enqueueStartupSync() 
            } else {
                Log.i(TAG, "SMS Sync is disabled. No work will be enqueued on boot.")
            }
        }
    }
}