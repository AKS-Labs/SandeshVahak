package com.akslabs.SandeshVahak.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences // Corrected import
import com.akslabs.SandeshVahak.services.SmsObserverService

/**
 * Receives restart intents (from AlarmManager) to bring the SmsObserverService back
 * after task removal or unexpected kill.
 */
class ServiceRestarterReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        try {
            // Initialize preferences if not already done
            try {
                Preferences.init(context.applicationContext)
            } catch (e: Exception) {
                Log.w("ServiceRestarter", "Preferences.init failed in ServiceRestarterReceiver", e)
            }
            
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (!isEnabled) {
                Log.w("ServiceRestarter", "SMS sync disabled; not restarting service")
                return
            }

            Log.i("ServiceRestarter", "Restart signal received; starting SmsObserverService")
            val serviceIntent = Intent(context, SmsObserverService::class.java)
            
            // Use a separate thread to avoid ANR
            Thread {
                try {
                    context.startForegroundService(serviceIntent)
                    Log.i("ServiceRestarter", "SmsObserverService started successfully")
                } catch (e: Exception) {
                    Log.e("ServiceRestarter", "Error starting SmsObserverService", e)
                    // Try regular start as fallback
                    try {
                        context.startService(serviceIntent)
                        Log.i("ServiceRestarter", "SmsObserverService started with fallback method")
                    } catch (fallbackException: Exception) {
                        Log.e("ServiceRestarter", "Error starting SmsObserverService with fallback", fallbackException)
                    }
                }
            }.start()
        } catch (e: Exception) {
            Log.e("ServiceRestarter", "Error restarting service", e)
        }
    }
}