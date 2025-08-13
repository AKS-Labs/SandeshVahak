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
            Intent.ACTION_MY_PACKAGE_REPLACED,
            Intent.ACTION_PACKAGE_REPLACED -> {
                Log.i(TAG, "Boot/Package event received: ${intent.action}")
                initializeSmsSync(context)
            }
        }
    }

    private fun initializeSmsSync(context: Context) {
        try {
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            val botToken = Preferences.getEncryptedString(Preferences.botToken, "")
            val channelId = Preferences.getEncryptedLong(Preferences.channelId, 0)

            val autoStartAllowed = Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)

            if (autoStartAllowed && isEnabled && botToken.isNotBlank() && channelId != 0L) {
                Log.i(TAG, "Initializing SMS sync after boot (enabled by user)")

                // Ensure notification channels exist before starting FGS
                NotificationHelper.createNotificationChannels(context)

                // Start SMS observer service
                val serviceIntent = Intent(context, SmsObserverService::class.java)
                context.startForegroundService(serviceIntent)

                // Check mode to decide periodic scheduling
                val mode = Preferences.getString(Preferences.smsSyncModeKey, "ALL")
                if (mode == "ALL") {
                    // Schedule periodic full SMS sync
                    WorkModule.SmsSync.enqueue()
                } else {
                    // Ensure periodic full sync is cancelled in NEW_ONLY mode
                    WorkModule.SmsSync.cancel()
                }

                // Always ensure keep-alive worker is scheduled
                WorkModule.SmsSync.enqueueKeepAlive()

                Log.i(TAG, "SMS sync services configured after boot")
            } else {
                Log.w(TAG, "Auto-start disabled or SMS sync not configured; skipping boot initialization")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing SMS sync after boot", e)
        }
    }
}
