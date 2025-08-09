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

        // Ensure preferences are initialized with robust error handling
        var preferencesInitialized = false
        try {
            com.akslabs.SandeshVahak.data.localdb.Preferences.init(applicationContext)
            preferencesInitialized = true
            Log.d(TAG, "Preferences initialized successfully in SmsObserverService")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize preferences in SmsObserverService", e)
            // Try to continue with fallback to SharedPreferences
        }

        // Check if user enabled sync with fallback logic
        val isEnabled = if (preferencesInitialized) {
            try {
                com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
                    com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
                    false
                )
            } catch (e: Exception) {
                Log.e(TAG, "Failed to read SMS sync enabled preference from Preferences, trying SharedPreferences", e)
                // Fallback to direct SharedPreferences access
                try {
                    val prefs = applicationContext.getSharedPreferences("preferences", MODE_PRIVATE)
                    prefs.getBoolean("is_sms_sync_enabled", false)
                } catch (fallbackException: Exception) {
                    Log.e(TAG, "Complete failure reading sync preference, defaulting to false", fallbackException)
                    false
                }
            }
        } else {
            // Fallback to direct SharedPreferences access if Preferences init failed
            try {
                val prefs = applicationContext.getSharedPreferences("preferences", MODE_PRIVATE)
                val enabled = prefs.getBoolean("is_sms_sync_enabled", false)
                Log.d(TAG, "Read SMS sync preference from fallback SharedPreferences: $enabled")
                enabled
            } catch (e: Exception) {
                Log.e(TAG, "Complete failure reading sync preference from fallback, defaulting to false", e)
                false
            }
        }

        Log.d(TAG, "SMS sync enabled: $isEnabled")

        if (isEnabled) {
            Log.d(TAG, "Starting SmsContentObserver from service onCreate")
            SmsContentObserver.startObserving(this)
        } else {
            Log.i(TAG, "SMS sync disabled; not starting content observer")
            stopSelf()
            return
        }
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
