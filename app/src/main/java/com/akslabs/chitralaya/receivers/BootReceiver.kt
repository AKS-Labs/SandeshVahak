package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.chitralaya.services.SmsObserverService
import com.akslabs.Suchak.workers.WorkModule

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
            // Check if SMS sync is enabled (if bot token and channel are configured)
            val botToken = Preferences.getEncryptedString(Preferences.botToken, "")
            val channelId = Preferences.getEncryptedLong(Preferences.channelId, 0)
            
            if (botToken.isNotBlank() && channelId != 0L) {
                Log.i(TAG, "Initializing SMS sync after boot")
                
                // Start SMS observer service
                val serviceIntent = Intent(context, SmsObserverService::class.java)
                context.startForegroundService(serviceIntent)
                
                // Schedule periodic SMS sync
                WorkModule.SmsSync.enqueue()
                
                // SMS sync is handled by SmsObserverService
                
                Log.i(TAG, "SMS sync services restarted after boot")
            } else {
                Log.w(TAG, "SMS sync not configured, skipping initialization")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing SMS sync after boot", e)
        }
    }
}
