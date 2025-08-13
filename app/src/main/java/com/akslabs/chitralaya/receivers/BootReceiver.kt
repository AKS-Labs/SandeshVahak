package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.chitralaya.services.SmsObserverService
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.Suchak.utils.NotificationHelper

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
                initializeSmsSync(context)
            }
        }
    }

    private fun initializeSmsSync(context: Context) {
        try {
            // Initialize preferences (so non-encrypted prefs are available at boot)
            try {
                Preferences.init(context.applicationContext)
            } catch (e: Exception) {
                Log.w(TAG, "Preferences.init failed in BootReceiver", e)
            }

            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            // Encrypted prefs may not be accessible before user unlock; guard access
            val botToken = runCatching { Preferences.getEncryptedString(Preferences.botToken, "") }.getOrDefault("")
            val channelId = runCatching { Preferences.getEncryptedLong(Preferences.channelId, 0) }.getOrDefault(0)

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)

            if (autoStartAllowed && isEnabled) {
                Log.i(TAG, "Initializing SMS sync after boot (enabled by user)")

                // Ensure notification channels exist before starting FGS
                NotificationHelper.createNotificationChannels(context)

                // Start SMS observer service
                val isAndroid15 = android.os.Build.VERSION.SDK_INT >= 35
                try {
                    val serviceIntent = Intent(context, SmsObserverService::class.java)
                    if (!isAndroid15 && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        context.startForegroundService(serviceIntent)
                    } else {
                        // On Android 15+, background start may be restricted; show prompt
                        if (isAndroid15) {
                            NotificationHelper.showPostBootNotification(context)
                        } else {
                            context.startService(serviceIntent)
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to start SmsObserverService at boot", e)
                }

                // Check mode to decide periodic scheduling; schedule after user unlocked
                // We also listen for ACTION_USER_UNLOCKED to call initializeSmsSync again
                try {
                    val mode = Preferences.getString(Preferences.smsSyncModeKey, "ALL")
                    if (mode == "ALL") {
                        WorkModule.SmsSync.enqueue()
                    } else {
                        WorkModule.SmsSync.cancel()
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to schedule periodic sync at boot; will retry on USER_UNLOCKED", e)
                }

                // Always ensure keep-alive worker is scheduled
                WorkModule.SmsSync.enqueueKeepAlive()

                Log.i(TAG, "SMS sync services configured after boot")
            } else {
                Log.w(TAG, "Auto-start disabled or SMS sync disabled; skipping boot initialization")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing SMS sync after boot", e)
        }
    }
}
