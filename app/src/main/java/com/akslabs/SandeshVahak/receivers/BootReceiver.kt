package com.akslabs.SandeshVahak.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.UserManager
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.utils.NotificationHelper // Kept for potential future use or if createNotificationChannels is called
import com.akslabs.SandeshVahak.workers.BootServiceStartWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "BootReceiver_DEBUG"
        // Flag to ensure initialization only happens once after the first unlock post-boot
        private var isUserUnlockedAtleastOnce = false
        // Flag to prevent re-initialization attempts if multiple boot-related intents arrive closely
        private var isInitializationAttemptedPostBoot = false
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive ENTRY - Action: ${intent.action}, isUserUnlockedAtleastOnce: $isUserUnlockedAtleastOnce, isInitAttempted: $isInitializationAttemptedPostBoot")

        val action = intent.action
        val userManager = context.getSystemService(Context.USER_SERVICE) as UserManager
        val isUserCurrentlyUnlocked = userManager.isUserUnlocked

        if (isUserCurrentlyUnlocked) {
            if (!isUserUnlockedAtleastOnce) {
                Log.i(TAG, "User is now unlocked. Setting isUserUnlockedAtleastOnce to true.")
                isUserUnlockedAtleastOnce = true
            }
        }

        when (action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_LOCKED_BOOT_COMPLETED, // Will be handled, but full init waits for unlock
            Intent.ACTION_USER_UNLOCKED,
            Intent.ACTION_MY_PACKAGE_REPLACED -> {

                // For ACTION_BOOT_COMPLETED, if we already tried initializing (e.g., due to a quick USER_UNLOCKED), skip.
                if (action == Intent.ACTION_BOOT_COMPLETED && isInitializationAttemptedPostBoot && isUserUnlockedAtleastOnce) {
                    Log.w(TAG, "BOOT_COMPLETED received, but initialization already done post-unlock. Skipping.")
                    return
                }
                
                // If user becomes unlocked, and we haven't tried init yet, OR if it's BOOT_COMPLETED and user is already unlocked
                if (isUserUnlockedAtleastOnce && !isInitializationAttemptedPostBoot) {
                    isInitializationAttemptedPostBoot = true // Set flag to prevent re-runs from subsequent related intents
                    Log.i(TAG, "Conditions met for initialization (User unlocked, init not yet attempted). Action: $action")
                    
                    val pendingResult: PendingResult = goAsync()

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            Log.d(TAG, "Coroutine for initializeSmsSync START (Action: $action)")
                            initializeSmsSync(context.applicationContext)
                            Log.d(TAG, "Coroutine for initializeSmsSync END (Action: $action)")
                        } finally {
                            pendingResult.finish()
                        }
                    }
                } else if (!isUserUnlockedAtleastOnce) {
                    Log.i(TAG, "Device not yet unlocked for the first time post-boot. Deferring initialization. Action: $action")
                    // If BOOT_COMPLETED arrives before USER_UNLOCKED, ensure isInitializationAttemptedPostBoot is false
                    // so that the subsequent USER_UNLOCKED can trigger initialization.
                    if (action == Intent.ACTION_BOOT_COMPLETED) {
                         isInitializationAttemptedPostBoot = false
                    }
                } else if (isInitializationAttemptedPostBoot) {
                     Log.i(TAG, "Initialization already attempted/done post-unlock. Skipping for action: $action")
                }
            }
            else -> {
                Log.w(TAG, "Received unhandled action: $action")
            }
        }
        Log.d(TAG, "onReceive EXIT - Action: $action")
    }

    private fun initializeSmsSync(context: Context) {
        Log.d(TAG, "initializeSmsSync START")

        try {
            Log.d(TAG, "Attempting Preferences.init()")
            Preferences.init(context) 
            Log.d(TAG, "Preferences.init() successful.")

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)

            Log.i(TAG, "Preference state: autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled")

            if (autoStartAllowed && isEnabled) {
                Log.i(TAG, "Sync conditions met. Proceeding to schedule service start worker.")
                
                // Ensure notification channels are created. SmsObserverService also does this, but good to have fallback.
                // NotificationHelper.createNotificationChannels(context) 

                Log.d(TAG, "Attempting to enqueue BootServiceStartWorker")
                try {
                    val bootServiceStartWorkRequest = OneTimeWorkRequestBuilder<BootServiceStartWorker>().build()
                    WorkManager.getInstance(context).enqueueUniqueWork(
                        BootServiceStartWorker.UNIQUE_WORK_NAME,
                        ExistingWorkPolicy.KEEP, // KEEP: If work already exists, do nothing. Suitable for boot operation.
                        bootServiceStartWorkRequest
                    )
                    Log.i(TAG, "BootServiceStartWorker enqueued successfully.")
                } catch (e: Exception) {
                    Log.e(TAG, "BootServiceStartWorker enqueue FAILED.", e)
                    // NotificationHelper.showServiceStartFailedNotification(context) // Optional: Notify if enqueueing fails
                }
            } else {
                Log.w(TAG, "Sync conditions NOT met (autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isEnabled). No service start scheduled.")
                // If sync is not supposed to run, cancel any potentially scheduled BootServiceStartWorker
                WorkManager.getInstance(context).cancelUniqueWork(BootServiceStartWorker.UNIQUE_WORK_NAME)
                Log.i(TAG, "Cancelled any pending BootServiceStartWorker as sync conditions not met.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "CRITICAL error in initializeSmsSync. Check for storage access or other init issues.", e)
        }
        Log.d(TAG, "initializeSmsSync END")
    }
}