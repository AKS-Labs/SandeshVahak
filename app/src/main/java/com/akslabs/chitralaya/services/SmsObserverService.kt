package com.akslabs.SandeshVahak.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.ui.MainActivity
import com.akslabs.SandeshVahak.workers.SmsSyncWorker
import com.akslabs.SandeshVahak.workers.WorkModule // Added import for KeepAliveWorker
import com.akslabs.SandeshVahak.services.SmsContentObserver

class SmsObserverService : Service() {

    private lateinit var preferences: SharedPreferences
    private var isServiceManuallyStopped = false

    companion object {
        private const val TAG = "SmsObsService_DEBUG"
        private const val SERVICE_NOTIFICATION_ID = 18697
        private const val SERVICE_CHANNEL_ID = "SmsObserverServiceChannel"
        const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
        const val UNIQUE_WORK_NAME = "SmsSyncWork"
        var isServiceCurrentlyActive = false // Flag to indicate service active state

        fun start(context: Context) {
            Log.d(TAG, "Companion.start() CALLED")
            val intent = Intent(context, SmsObserverService::class.java).apply {
                action = ACTION_START_SERVICE
            }
            Log.d(TAG, "Companion.start() - Attempting ContextCompat.startForegroundService()")
            try {
                ContextCompat.startForegroundService(context, intent)
                Log.d(TAG, "Companion.start() - ContextCompat.startForegroundService() called successfully.")
            } catch (e: Exception) {
                Log.e(TAG, "Companion.start() - ContextCompat.startForegroundService() FAILED.", e)
            }
            Log.d(TAG, "Companion.start() ENDED")
        }

        fun stop(context: Context) {
            Log.d(TAG, "Companion.stop() CALLED")
            val intent = Intent(context, SmsObserverService::class.java).apply {
                action = ACTION_STOP_SERVICE
            }
            Log.d(TAG, "Companion.stop() - Attempting context.startService() for stop action")
            try {
                context.startService(intent)
                Log.d(TAG, "Companion.stop() - context.startService() for stop action called successfully.")
            } catch (e: Exception) {
                Log.e(TAG, "Companion.stop() - context.startService() for stop action FAILED.", e)
            }
            Log.d(TAG, "Companion.stop() ENDED")
        }
    }

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        Log.d(TAG, "PreferenceChangeListener: Key '$key' changed.")
        when (key) {
            Preferences.isSmsSyncEnabledKey,
            Preferences.smsSyncModeKey -> {
                Log.i(TAG, "Relevant preference changed: $key. Calling updateSyncStateBasedOnPreferences().")
                updateSyncStateBasedOnPreferences()
            }
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate() CALLED")
        isServiceCurrentlyActive = true // Service is now active
        super.onCreate()
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        createNotificationChannel()
        Log.i(TAG, "onCreate() COMPLETED.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d(TAG, "onStartCommand() CALLED - Action: $action, StartId: $startId, Flags: $flags")

        when (action) {
            ACTION_START_SERVICE -> {
                Log.i(TAG, "onStartCommand() - ACTION_START_SERVICE received.")
                isServiceManuallyStopped = false
                val notification = createServiceNotification("SMS Sync service is active...")
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        startForeground(SERVICE_NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
                    } else {
                        startForeground(SERVICE_NOTIFICATION_ID, notification)
                    }
                    Log.i(TAG, "onStartCommand() - startForeground() successful.")
                } catch (e: Exception) {
                    Log.e(TAG, "onStartCommand() - startForeground() FAILED.", e)
                    stopSelfResult(startId)
                    return START_NOT_STICKY
                }
                updateSyncStateBasedOnPreferences()
                SmsContentObserver.startObserving(this)
                Log.i(TAG, "SmsContentObserver observing started (ACTION_START_SERVICE).")
            }
            ACTION_STOP_SERVICE -> {
                Log.i(TAG, "onStartCommand() - ACTION_STOP_SERVICE received.")
                isServiceManuallyStopped = true
                SmsContentObserver.stopObserving(this)
                Log.i(TAG, "SmsContentObserver observing stopped (ACTION_STOP_SERVICE).")
                WorkModule.SmsSync.cancelKeepAlive() // Corrected
                Log.d(TAG, "KeepAliveWorker cancelled due to ACTION_STOP_SERVICE.")
                stopSmsSyncOperations()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelfResult(startId)
                Log.d(TAG, "onStartCommand() - ACTION_STOP_SERVICE processed. Returning START_NOT_STICKY.")
                return START_NOT_STICKY
            }
            else -> { // Handles null intent or system restart
                Log.w(TAG, "onStartCommand() - Received unknown or null action. isServiceManuallyStopped: $isServiceManuallyStopped")
                if (!isServiceManuallyStopped) {
                    Log.i(TAG, "Service not manually stopped. Ensuring foreground state.")
                    val notification = createServiceNotification("SMS Sync service is active...")
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            startForeground(SERVICE_NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
                        } else {
                            startForeground(SERVICE_NOTIFICATION_ID, notification)
                        }
                        Log.i(TAG, "onStartCommand() [else] - startForeground() successful.")
                    } catch (e: Exception) {
                        Log.e(TAG, "onStartCommand() [else] - startForeground() FAILED.", e)
                        stopSelfResult(startId)
                        return START_NOT_STICKY
                    }
                    updateSyncStateBasedOnPreferences()
                    SmsContentObserver.startObserving(this)
                    Log.i(TAG, "SmsContentObserver observing started (service restart).")
                } else {
                    Log.i(TAG, "Service was manually stopped. Not restarting. Stopping.")
                    WorkModule.SmsSync.cancelKeepAlive() // Corrected
                    Log.d(TAG, "KeepAliveWorker cancelled as service was manually stopped and is stopping.")
                    stopForeground(STOP_FOREGROUND_REMOVE)
                    stopSelfResult(startId)
                    Log.d(TAG, "onStartCommand() [else] - Manually stopped. Returning START_NOT_STICKY.")
                    return START_NOT_STICKY
                }
            }
        }
        Log.d(TAG, "onStartCommand() ENDED. Returning START_STICKY.")
        return START_STICKY
    }

    private fun updateSyncStateBasedOnPreferences() {
        Log.d(TAG, "updateSyncStateBasedOnPreferences() CALLED")
        val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
        val currentSyncModePref = Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
        Log.i(TAG, "updateSyncState: isEnabled=$isSyncEnabled, mode=$currentSyncModePref, manuallyStopped=$isServiceManuallyStopped")

        if (isServiceManuallyStopped) {
            Log.i(TAG, "updateSyncState: Service is manually stopped. Cancelling work.")
            WorkModule.SmsSync.cancelKeepAlive() // Corrected
            Log.d(TAG, "KeepAliveWorker cancelled as service is manually stopped.")
            stopSmsSyncOperations()
            updateServiceNotification("SMS Sync service stopped.")
            Log.d(TAG, "updateSyncStateBasedOnPreferences() ENDED (manually stopped).")
            return
        }

        if (isSyncEnabled) {
            val workerSyncMode = when (currentSyncModePref) {
                Preferences.SMS_SYNC_MODE_ALL -> SmsSyncWorker.SYNC_MODE_FULL
                Preferences.SMS_SYNC_MODE_NEW_ONLY -> SmsSyncWorker.SYNC_MODE_CATCH_UP
                else -> {
                    Log.w(TAG, "Unknown sync mode pref: $currentSyncModePref. Defaulting to CATCH_UP.")
                    SmsSyncWorker.SYNC_MODE_CATCH_UP
                }
            }
            val workData = Data.Builder().putString(SmsSyncWorker.KEY_SYNC_MODE, workerSyncMode).build()
            val smsSyncWorkRequest = OneTimeWorkRequestBuilder<SmsSyncWorker>().setInputData(workData).build()

            Log.i(TAG, "updateSyncState: Enqueuing SMS sync work with mode: $workerSyncMode. Unique work: $UNIQUE_WORK_NAME")
            WorkManager.getInstance(applicationContext).enqueueUniqueWork(
                UNIQUE_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                smsSyncWorkRequest
            )
            updateServiceNotification("SMS Sync active. Monitoring for changes.")
            WorkModule.SmsSync.enqueueKeepAlive() // Corrected
            Log.d(TAG, "KeepAliveWorker enqueued as sync is enabled.")
        } else {
            Log.i(TAG, "updateSyncState: Sync is DISABLED. Cancelling work.")
            WorkModule.SmsSync.cancelKeepAlive() // Corrected
            Log.d(TAG, "KeepAliveWorker cancelled as sync is disabled.")
            stopSmsSyncOperations()
            updateServiceNotification("SMS Sync is disabled by preferences.")
        }
        Log.d(TAG, "updateSyncStateBasedOnPreferences() ENDED.")
    }

    private fun stopSmsSyncOperations() {
        Log.d(TAG, "stopSmsSyncOperations() CALLED")
        WorkManager.getInstance(applicationContext).cancelUniqueWork(UNIQUE_WORK_NAME)
        Log.i(TAG, "stopSmsSyncOperations: Cancelled WorkManager work with name $UNIQUE_WORK_NAME.")
        Log.d(TAG, "stopSmsSyncOperations() ENDED")
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind() CALLED. Returning null.")
        return null
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() CALLED")
        isServiceCurrentlyActive = false // Service is no longer active
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        SmsContentObserver.stopObserving(this)
        Log.i(TAG, "SmsContentObserver observing stopped (onDestroy).")
        WorkModule.SmsSync.cancelKeepAlive() // Corrected
        Log.d(TAG, "KeepAliveWorker cancelled in onDestroy.")
        Log.i(TAG, "onDestroy() COMPLETED.")
    }

    private fun createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel() CALLED")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                SERVICE_CHANNEL_ID,
                "SMS Observer Service Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Channel for the persistent SMS Observer Service" }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
            Log.d(TAG, "Service notification channel created/ensured.")
        }
        Log.d(TAG, "createNotificationChannel() ENDED")
    }

    private fun createServiceNotification(contentText: String): Notification {
        Log.d(TAG, "createServiceNotification() CALLED with text: $contentText")
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, pendingIntentFlags)

        val notification = NotificationCompat.Builder(this, SERVICE_CHANNEL_ID)
            .setContentTitle("SandeshVahak Service")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.cloud_arrow_up_solid)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setSilent(true)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .build()
        Log.d(TAG, "createServiceNotification() ENDED. Notification created.")
        return notification
    }

    private fun updateServiceNotification(contentText: String) {
        Log.d(TAG, "updateServiceNotification() CALLED with text: $contentText")
        if (!isServiceManuallyStopped || Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(SERVICE_NOTIFICATION_ID, createServiceNotification(contentText))
            Log.d(TAG, "Service notification updated.")
        } else {
            Log.d(TAG, "Service notification NOT updated (service stopped or sync disabled).")
        }
        Log.d(TAG, "updateServiceNotification() ENDED.")
    }
}
