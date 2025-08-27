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

        // Immediately promote to foreground to satisfy OS deadline at boot (Android 15 stricter)
        try {
            com.akslabs.Suchak.utils.NotificationHelper.createNotificationChannels(this)
            val notification = NotificationHelper.createSmsObserverNotification(
                this,
                "SMS Sync Active",
                "Monitoring for new SMS messages"
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
            } else {
                startForeground(NOTIFICATION_ID, notification)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start foreground immediately in onCreate", e)
        }

        // Ensure preferences are initialized with robust error handling
        var preferencesInitialized = false
        try {
            // Use device-protected storage context for direct boot compatibility
            val ctx = applicationContext.createDeviceProtectedStorageContext()
            com.akslabs.SandeshVahak.data.localdb.Preferences.init(ctx)
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
                    prefs.getBoolean("isSmsSyncEnabled", false)  // Use consistent key
                } catch (fallbackException: Exception) {
                    Log.e(TAG, "Complete failure reading sync preference, defaulting to false", fallbackException)
                    false
                }
            }
        } else {
            // Fallback to direct SharedPreferences access if Preferences init failed
            try {
                val prefs = applicationContext.getSharedPreferences("preferences", MODE_PRIVATE)
                val enabled = prefs.getBoolean("isSmsSyncEnabled", false)  // Use consistent key
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
            // Don't stop service immediately - let it stay ready for when sync is enabled
            // The service will be restarted when user enables sync
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "SMS Observer Service started with flags=$flags, startId=$startId")

        // Ensure preferences are initialized and observer is registered even after restarts
        var isEnabled = false
        try {
            com.akslabs.SandeshVahak.data.localdb.Preferences.init(applicationContext)
            isEnabled = com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
                com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
                false
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to init/read Preferences in onStartCommand", e)
        }
        
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
        
        // Start or re-start the content observer if enabled
        if (isEnabled) {
            try {
                Log.d(TAG, "Ensuring SmsContentObserver is registered from onStartCommand")
                SmsContentObserver.startObserving(this)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to start SmsContentObserver from onStartCommand", e)
            }
        } else {
            Log.i(TAG, "SMS sync disabled; observer not started from onStartCommand")
        }

        // Return START_STICKY to restart service if killed
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.w(TAG, "onTaskRemoved called - scheduling restart")
        scheduleSelfRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "SMS Observer Service destroyed")
        
        // Stop observing SMS content changes
        SmsContentObserver.stopObserving(this)

        // Schedule restart if destroyed unexpectedly
        scheduleSelfRestart()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun scheduleSelfRestart() {
        try {
            val context = applicationContext
            val intent = Intent(context, com.akslabs.chitralaya.receivers.ServiceRestarterReceiver::class.java)
            val pendingIntent = android.app.PendingIntent.getBroadcast(
                context,
                0,
                intent,
                android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
            )

            val alarmManager = context.getSystemService(android.content.Context.ALARM_SERVICE) as android.app.AlarmManager
            val triggerAt = System.currentTimeMillis() + 2_000L
            val clock = android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
            val elapsed = android.os.SystemClock.elapsedRealtime() + 2_000L

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(clock, elapsed, pendingIntent)
            } else {
                alarmManager.setExact(clock, elapsed, pendingIntent)
            }
            Log.i(TAG, "Scheduled service restart in 2s via AlarmManager")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to schedule service restart", e)
        }
    }
}