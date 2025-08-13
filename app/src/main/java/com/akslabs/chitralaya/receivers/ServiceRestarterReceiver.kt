package com.akslabs.chitralaya.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.chitralaya.services.SmsObserverService

/**
 * Receives restart intents (from AlarmManager) to bring the SmsObserverService back
 * after task removal or unexpected kill.
 */
class ServiceRestarterReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        try {
            val isEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (!isEnabled) {
                Log.w("ServiceRestarter", "SMS sync disabled; not restarting service")
                return
            }

            Log.i("ServiceRestarter", "Restart signal received; starting SmsObserverService")
            val serviceIntent = Intent(context, SmsObserverService::class.java)
            context.startForegroundService(serviceIntent)
        } catch (e: Exception) {
            Log.e("ServiceRestarter", "Error restarting service", e)
        }
    }
}


