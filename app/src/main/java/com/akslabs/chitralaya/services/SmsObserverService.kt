package com.akslabs.chitralaya.services

import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.akslabs.Suchak.utils.NotificationHelper

/**
 * Background service to observe SMS changes
 */
class SmsObserverService : Service() {

    companion object {
        private const val TAG = "SmsObserverService"
        private const val NOTIFICATION_ID = 3001
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "SMS Observer Service created")
        
        // Start observing SMS content changes
        SmsContentObserver.startObserving(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "SMS Observer Service started")
        
        // Create foreground notification to keep service alive
        val notification = NotificationHelper.createSmsObserverNotification(
            this,
            "SMS Sync Active",
            "Monitoring for new SMS messages"
        )
        
        // Start foreground service with proper type for Android 14+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
        
        // Return START_STICKY to restart service if killed
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "SMS Observer Service destroyed")
        
        // Stop observing SMS content changes
        SmsContentObserver.stopObserving(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
