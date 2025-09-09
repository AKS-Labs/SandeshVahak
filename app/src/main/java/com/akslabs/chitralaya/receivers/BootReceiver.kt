package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.akslabs.chitralaya.data.localdb.Preferences
import com.akslabs.chitralaya.workers.WorkModule
import com.akslabs.chitralaya.utils.NotificationHelper

/**
 * Broadcast receiver to restart SMS syncing after device boot
 */
class BootReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "BootReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_LOCKED_BOOT_COMPLETED,
            Intent.ACTION_USER_UNLOCKED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_PACKAGE_REPLACED -> {
                Log.i(TAG, "Boot/Package event received: ${intent.action}")
                // Using a background thread to ensure Preferences.init doesn't block UI thread if called elsewhere.
                // WorkManager operations are already off the main thread.
                Thread {
                    initializeSmsSync(context, intent.action == Intent.ACTION_USER_UNLOCKED)
                }.start()
            }
        }
    }

    private fun initializeSmsSync(context: Context, isUserUnlocked: Boolean) {
        try {
            // Initialize preferences
            try {
                Preferences.init(context.applicationContext)
                Log.d(TAG, "Preferences initialized in BootReceiver.")
            } catch (e: Exception) {
                Log.e(TAG, "Preferences.init failed critically in BootReceiver. Cannot proceed.", e)
                return // Critical failure, cannot determine sync state
            }

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)

            Log.i(TAG, "BootReceiver state: autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled, isUserUnlocked=$isUserUnlocked (UserUnlocked state is informative here, actual preference access critical)")

            if (autoStartAllowed && isEnabled) {
                Log.i(TAG, "SMS sync is enabled and auto-start is allowed. Proceeding with boot initialization.")

                // Ensure notification channels exist (good practice, especially for the post-boot prompt)
                NotificationHelper.createNotificationChannels(context)
                
                // For Android 15 (API 35) and above, only show a notification to prompt the user.
                // For older versions, proceed to start background workers.
                if (Build.VERSION.SDK_INT >= 35) { // Assuming API 35 for Android 15+
                    Log.i(TAG, "Android 15+ (API ${Build.VERSION.SDK_INT}) detected. Showing post-boot notification prompt ONLY.")
                    NotificationHelper.showPostBootNotification(context)
                } else {
                    Log.i(TAG, "Android version < 15 (API ${Build.VERSION.SDK_INT}). Proceeding with worker enqueueing.")
                    
                    // Enqueue StartupSyncWorker to handle catch-up and observer registration.
                    // This worker will run once after boot if conditions are met.
                    Log.i(TAG, "Enqueueing StartupSyncWorker.")
                    WorkModule.SmsSync.enqueueStartupSync()

                    // Schedule periodic full sync if in "ALL" mode (for older Android versions).
                    // This is a long-term periodic worker, distinct from the one-time startup sync.
                    try {
                        val mode = Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
                        if (mode == Preferences.SMS_SYNC_MODE_ALL) {
                            Log.i(TAG, "Sync mode is ALL. Enqueueing periodic SmsSyncWorker.")
                            WorkModule.SmsSync.enqueue()
                        } else {
                            // If not "ALL", ensure the periodic full sync worker is cancelled.
                            Log.i(TAG, "Sync mode is not ALL ($mode). Cancelling periodic SmsSyncWorker if it exists.")
                            WorkModule.SmsSync.cancel()
                        }
                    } catch (e: Exception) {
                        Log.w(TAG, "Failed to schedule/cancel periodic sync at boot due to preference access issues; StartupSyncWorker should handle initial state.", e)
                    }

                    // Ensure keep-alive worker is scheduled (for older Android versions). This is also a periodic worker.
                    Log.i(TAG, "Enqueueing KeepAliveWorker.")
                    WorkModule.SmsSync.enqueueKeepAlive()

                    Log.i(TAG, "SMS sync workers (Startup, Periodic, KeepAlive) have been configured after boot for older Android versions.")
                }
            } else {
                Log.w(TAG, "Auto-start disabled ($autoStartAllowed) or SMS sync disabled ($isEnabled); skipping boot initialization and cancelling workers.")
                // If sync was previously enabled and now disabled, or auto-start is off, cancel relevant workers.
                WorkModule.SmsSync.cancel() // Cancel periodic full sync
                WorkModule.SmsSync.cancelKeepAlive() // Cancel keep-alive
                // One-time workers like StartupSyncWorker or InstantSmsSyncWorker run to completion or are replaced,
                // so specific cancellation here might not be necessary unless they were uniquely named and we want to stop a pending one.
                Log.i(TAG,"Cancelled periodic and keep-alive workers as sync is not active on boot.")
            }
        } catch (e: Exception) {
            // Catch any other unexpected errors during initialization.
            Log.e(TAG, "Error initializing SMS sync after boot", e)
        }
    }
}
