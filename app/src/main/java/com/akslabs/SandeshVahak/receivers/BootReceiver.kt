package com.akslabs.SandeshVahak.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService
import com.akslabs.SandeshVahak.utils.NotificationHelper
import com.akslabs.SandeshVahak.workers.WorkModule

class BootReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "BootReceiver_DEBUG"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // ADD THIS LINE:
        Log.i(TAG, "IMMEDIATE onReceive ENTRY - Action: ${intent.action}")

        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_LOCKED_BOOT_COMPLETED, // Will still log entry, but logic below might wait
            Intent.ACTION_USER_UNLOCKED,
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_PACKAGE_REPLACED -> {
                Log.i(TAG, "Boot/Package event received: ${intent.action}. Starting new thread for initialization.")
                
                // Only proceed with full initialization if user is unlocked or it's a regular boot completed
                // (for non-Direct Boot aware part or if device is already unlocked)
                // or if it's a package replacement event.
                if (intent.action == Intent.ACTION_USER_UNLOCKED ||
                    intent.action == Intent.ACTION_BOOT_COMPLETED || // BOOT_COMPLETED might fire after unlock on some devices/setups
                    intent.action == Intent.ACTION_MY_PACKAGE_REPLACED || // Package updates usually happen when unlocked
                    intent.action == Intent.ACTION_PACKAGE_REPLACED) {

                    Thread {
                        Log.d(TAG, "Thread for initializeSmsSync START (Action: ${intent.action})")
                        // Pass a flag indicating if storage is likely unlocked
                        initializeSmsSync(context.applicationContext, true)
                        Log.d(TAG, "Thread for initializeSmsSync END (Action: ${intent.action})")
                    }.start()

                } else if (intent.action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
                    Log.i(TAG, "Device is in LOCKED_BOOT_COMPLETED state. Deferring full sync initialization until USER_UNLOCKED.")
                    // Optional: You could enqueue a very simple worker here that waits for user unlock
                    // or simply rely on the USER_UNLOCKED broadcast to come later.
                    // For now, we'll just log and wait for USER_UNLOCKED.
                    // You could also try initializing Preferences in device-protected storage if needed here.
                }
            }
            else -> {
                Log.w(TAG, "Received unhandled action: ${intent.action}")
            }
        }
        Log.d(TAG, "onReceive END - Action: ${intent.action}")
    }

    // Changed isUserUnlocked to isStorageLikelyUnlocked for clarity
    private fun initializeSmsSync(context: Context, isStorageLikelyUnlocked: Boolean) {
        Log.d(TAG, "initializeSmsSync START - isStorageLikelyUnlocked: $isStorageLikelyUnlocked")
        
        if (!isStorageLikelyUnlocked) {
            Log.w(TAG, "initializeSmsSync: Storage not likely unlocked. Aborting preference-dependent sync.")
            // Potentially show a notification or schedule a check for later when user unlocks.
            return
        }

        try {
            Log.d(TAG, "Attempting Preferences.init()")
            Preferences.init(context) // This requires credential-encrypted storage
            Log.d(TAG, "Preferences.init() successful.")

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)

            Log.i(TAG, "Preference state: autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled")

            if (autoStartAllowed && isEnabled) {
                Log.i(TAG, "Sync conditions met. Proceeding to start service.")

                Log.d(TAG, "Attempting NotificationHelper.createNotificationChannels()")
                NotificationHelper.createNotificationChannels(context)
                Log.d(TAG, "NotificationHelper.createNotificationChannels() successful.")
                
                Log.d(TAG, "Attempting SmsObserverService.start()")
                try {
                    SmsObserverService.start(context)
                    Log.i(TAG, "SmsObserverService.start() called successfully.")
                } catch (e: Exception) {
                    Log.e(TAG, "SmsObserverService.start() FAILED.", e)
                    Log.d(TAG, "Attempting to show service start failure notification.")
                    NotificationHelper.showServiceStartFailedNotification(context)
                    Log.d(TAG, "Service start failure notification call completed.")
                }
            } else {
                Log.w(TAG, "Sync conditions NOT met (autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled). Cancelling workers.")
                // Cancel workers if sync is not supposed to run
                // WorkModule.SmsSync.cancel() // Let's be careful with cancelling; ensure this is the desired logic
                // WorkModule.SmsSync.cancelKeepAlive()
                Log.i(TAG,"Sync not started due to preference settings. Consider if workers should be cancelled here.")
            }
        } catch (e: Exception) {
            // Catching exceptions during Preferences.init() if storage isn't ready.
            Log.e(TAG, "CRITICAL error in initializeSmsSync. Potentially due to storage access before unlock.", e)
        }
        Log.d(TAG, "initializeSmsSync END")
    }
}
