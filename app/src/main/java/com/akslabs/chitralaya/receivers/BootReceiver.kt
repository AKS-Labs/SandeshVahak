package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.chitralaya.data.localdb.Preferences
// SmsObserverService import might not be directly needed here anymore
// import com.akslabs.chitralaya.services.SmsObserverService 
import com.akslabs.chitralaya.workers.WorkModule // Corrected import
import com.akslabs.chitralaya.utils.NotificationHelper // Corrected import

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
            Intent.ACTION_LOCKED_BOOT_COMPLETED, // It's good to handle this too
            Intent.ACTION_USER_UNLOCKED, // Important for accessing encrypted preferences
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_PACKAGE_REPLACED -> {
                Log.i(TAG, "Boot/Package event received: ${intent.action}")
                // Using a background thread is good, WorkManager also operates off the main thread.
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
                Log.w(TAG, "Preferences.init failed in BootReceiver", e)
                // If prefs fail, it's risky to proceed.
                return
            }

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)

            Log.i(TAG, "BootReceiver state: autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled, isUserUnlocked=$isUserUnlocked")

            if (autoStartAllowed && isEnabled) {
                Log.i(TAG, "SMS sync is enabled and auto-start is allowed. Proceeding with boot initialization.")

                // Ensure notification channels exist (good practice)
                NotificationHelper.createNotificationChannels(context)
                
                // On Android 15+, if background start is restricted, prompt user.
                // This check can remain as a safeguard.
                if (android.os.Build.VERSION.SDK_INT >= 35 /* Android 15 V */) {
                    // Consider if this notification is needed if StartupSyncWorker handles its own.
                    // For now, keeping it as a direct user prompt on boot for these restricted OS versions.
                    Log.i(TAG, "Android 15+ detected. Showing post-boot notification prompt.")
                    NotificationHelper.showPostBootNotification(context)
                }

                // Enqueue StartupSyncWorker to handle catch-up and observer registration.
                // This worker will run once after boot if conditions are met.
                Log.i(TAG, "Enqueueing StartupSyncWorker.")
                WorkModule.SmsSync.enqueueStartupSync()

                // The following can be conditional on user unlock if they access sensitive preferences
                // or if StartupSyncWorker itself needs user to be unlocked for full functionality.
                // For now, assuming StartupSyncWorker handles its own preference needs.

                // Schedule periodic full sync if in "ALL" mode.
                // This is a long-term periodic worker, distinct from the one-time startup sync.
                try {
                    val mode = Preferences.getString(Preferences.smsSyncModeKey, "NEW_ONLY") // Default to NEW_ONLY if not set
                    if (mode == "ALL") {
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

                // Ensure keep-alive worker is scheduled. This is also a periodic worker.
                Log.i(TAG, "Enqueueing KeepAliveWorker.")
                WorkModule.SmsSync.enqueueKeepAlive()

                Log.i(TAG, "SMS sync workers (Startup, Periodic, KeepAlive) have been configured after boot.")

            } else {
                Log.w(TAG, "Auto-start disabled ($autoStartAllowed) or SMS sync disabled ($isEnabled); skipping boot initialization.")
                // If sync was previously enabled and now disabled, or auto-start is off, cancel relevant workers.
                WorkModule.SmsSync.cancel() // Cancel periodic full sync
                WorkModule.SmsSync.cancelKeepAlive() // Cancel keep-alive
                // One-time workers like StartupSyncWorker or InstantSmsSyncWorker run to completion or are replaced,
                // so specific cancellation here might not be necessary unless they were uniquely named and we want to stop a pending one.
                Log.i(TAG,"Cancelled periodic and keep-alive workers as sync is not active on boot.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing SMS sync after boot", e)
        }
    }
}