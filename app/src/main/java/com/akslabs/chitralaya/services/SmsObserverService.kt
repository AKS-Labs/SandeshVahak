package com.akslabs.SandeshVahak.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.ui.MainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive

class SmsObserverService : Service() {

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    private var currentSyncJob: Job? = null

    private lateinit var preferences: SharedPreferences
    private var isServiceManuallyStopped = false

    companion object {
        private const val TAG = "SmsObserverService"
        private const val NOTIFICATION_ID = 18697
        private const val CHANNEL_ID = "SmsObserverServiceChannel"
        const val ACTION_START_SERVICE = "ACTION_START_SERVICE"
        const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

        fun start(context: Context) {
            Log.d(TAG, "Static start() called")
            val intent = Intent(context, SmsObserverService::class.java).apply {
                action = ACTION_START_SERVICE
            }
            ContextCompat.startForegroundService(context, intent)
        }

        fun stop(context: Context) {
            Log.d(TAG, "Static stop() called")
            val intent = Intent(context, SmsObserverService::class.java).apply {
                action = ACTION_STOP_SERVICE
            }
            context.stopService(intent)
        }
    }

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        Log.d(TAG, "PreferenceChangeListener: Key '$key' changed.")
        when (key) {
            Preferences.isSmsSyncEnabledKey,
            Preferences.smsSyncModeKey,
            Preferences.smsSyncEnabledSinceKey -> {
                Log.i(TAG, "Relevant preference changed: $key. Updating sync state.")
                updateSyncStateBasedOnPreferences()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: Service creating.")
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE) 
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        createNotificationChannel()
        Log.i(TAG, "onCreate: Preference change listener registered.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: Received action: ${intent?.action}, startId: $startId")
        when (intent?.action) {
            ACTION_START_SERVICE -> {
                isServiceManuallyStopped = false
                Log.i(TAG, "onStartCommand: ACTION_START_SERVICE. Starting foreground and updating sync state.")
                startForeground(NOTIFICATION_ID, createNotification("Initializing SMS Sync..."))
                updateSyncStateBasedOnPreferences()
            }
            ACTION_STOP_SERVICE -> {
                Log.i(TAG, "onStartCommand: ACTION_STOP_SERVICE. Stopping sync operations and service.")
                isServiceManuallyStopped = true
                stopSmsSyncOperations()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelfResult(startId)
                return START_NOT_STICKY
            }
            else -> {
                 Log.w(TAG, "onStartCommand: Received unknown or null action ($flags, $startId). Starting service and checking prefs if not manually stopped.")
                 if (!isServiceManuallyStopped) {
                    startForeground(NOTIFICATION_ID, createNotification("Initializing SMS Sync..."))
                    updateSyncStateBasedOnPreferences()
                 } else {
                    Log.i(TAG, "Service was manually stopped, not restarting via null action.")
                    stopForeground(STOP_FOREGROUND_REMOVE)
                    stopSelfResult(startId)
                    return START_NOT_STICKY
                 }
            }
        }
        return START_STICKY
    }

    private fun updateSyncStateBasedOnPreferences(isInitialCall: Boolean = false) {
        val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
        val currentSyncMode = Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
        val syncEnabledSince = Preferences.getLong(Preferences.smsSyncEnabledSinceKey, 0L)

        Log.i(TAG, "updateSyncStateBasedOnPreferences: isEnabled=$isSyncEnabled, mode=$currentSyncMode, since=$syncEnabledSince, manuallyStopped=$isServiceManuallyStopped, isInitialCall=$isInitialCall")

        currentSyncJob?.cancel("New sync state requested")

        if (isServiceManuallyStopped) {
            Log.i(TAG, "updateSyncStateBasedOnPreferences: Service was manually stopped. Not starting operations.")
            stopSmsSyncOperations() 
            return
        }

        if (isSyncEnabled) {
            when (currentSyncMode) {
                Preferences.SMS_SYNC_MODE_ALL -> {
                    Log.i(TAG, "updateSyncStateBasedOnPreferences: Sync ALL mode detected. Starting full sync.")
                    currentSyncJob = serviceScope.launch {
                        performFullSmsSync()
                    }
                }
                Preferences.SMS_SYNC_MODE_NEW_ONLY -> {
                    Log.i(TAG, "updateSyncStateBasedOnPreferences: Sync NEW_ONLY mode detected. Starting catch-up.")
                    startForeground(NOTIFICATION_ID, createNotification("Processing recent SMS..."))
                    currentSyncJob = serviceScope.launch {
                        performCatchUpSync()
                    }
                }
                else -> {
                    Log.w(TAG, "updateSyncStateBasedOnPreferences: Unknown sync mode: $currentSyncMode. Stopping sync.")
                    stopSmsSyncOperations()
                }
            }
        } else {
            Log.i(TAG, "updateSyncStateBasedOnPreferences: Sync is DISABLED. Stopping sync operations.")
            stopSmsSyncOperations()
        }
    }

    private suspend fun performFullSmsSync() {
        Log.i(TAG, "performFullSmsSync: User selected 'Sync ALL'. Initiating force sync.")
        startForeground(NOTIFICATION_ID, createNotification("Starting full SMS sync..."))

        var syncError: String? = null
        var totalMessagesProcessed = 0

        try {
            SmsSyncService.forceSync(applicationContext).collect { progress ->
                if (!serviceScope.isActive) throw CancellationException("Coroutine cancelled during fullSmsSync") // Use serviceScope.isActive
                totalMessagesProcessed = progress.totalMessagesSynced
                Log.i(TAG, "Full Sync Progress: Batch ${progress.currentBatch}, Synced $totalMessagesProcessed, Complete: ${progress.isComplete}, Error: ${progress.errorMessage}")
                if (progress.isComplete) {
                    if (progress.errorMessage != null) {
                        syncError = progress.errorMessage
                        updateNotification("Full sync failed: ${progress.errorMessage?.take(50)}")
                    } else {
                        updateNotification("Full SMS sync completed. $totalMessagesProcessed messages processed.")
                    }
                } else {
                    updateNotification("Syncing all: $totalMessagesProcessed messages so far...")
                }
            }
        } catch (e: CancellationException) {
            Log.i(TAG, "performFullSmsSync: Coroutine was cancelled.")
            updateNotification("Full sync cancelled.")
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Exception during forceSync collection", e)
            syncError = e.message ?: "Unknown error during sync"
            updateNotification("Full sync critically failed: ${syncError?.take(50)}")
        }

        if (syncError == null && serviceScope.isActive) {  // Use serviceScope.isActive
            Log.i(TAG, "performFullSmsSync: Full sync process completed successfully ($totalMessagesProcessed messages). Switching to NEW_ONLY mode.")
            val newSyncEnabledSince = System.currentTimeMillis()
            Preferences.edit {
                putString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
                putLong(Preferences.smsSyncEnabledSinceKey, newSyncEnabledSince)
            }
        } else if (syncError != null){
            Log.e(TAG, "performFullSmsSync: Full sync encountered an error: $syncError. Not switching to NEW_ONLY mode automatically.")
        }
        Log.i(TAG, "performFullSmsSync: END")
    }

    private suspend fun performCatchUpSync() {
        Log.i(TAG, "performCatchUpSync: Starting.")
 
        Log.d(TAG, "Performing initial catch-up for NEW_ONLY mode.")
        try {
            val quickSyncResult = SmsSyncService.performQuickSync(applicationContext)
             if (!serviceScope.isActive) throw CancellationException("Coroutine cancelled during quickSyncResult") // Use serviceScope.isActive
            when (quickSyncResult) {
                is SmsSyncResult.Success -> Log.i(TAG, "Initial catch-up sync: ${quickSyncResult.messagesSynced} messages.")
                is SmsSyncResult.Error -> Log.e(TAG, "Initial catch-up sync error: ${quickSyncResult.message}")
                is SmsSyncResult.NoChannelConfigured -> Log.w(TAG, "Initial catch-up: No channel configured.")
            }
        } catch (e: CancellationException) {
            Log.i(TAG, "performCatchUpSync: Coroutine was cancelled during catch-up.")
            updateNotification("SMS catch-up cancelled.")
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Exception during initial catch-up sync", e)
             updateNotification("SMS catch-up failed: ${e.message?.take(50)}")
        }

        if (serviceScope.isActive) {  // Use serviceScope.isActive
            Log.i(TAG, "performCatchUpSync: Initial catch-up complete. Static SmsContentObserver is responsible for new messages.")
            updateNotification("SMS sync active. Monitoring new messages.")
        }
        Log.i(TAG, "performCatchUpSync: END")
    }

    private fun stopSmsSyncOperations() {
        Log.i(TAG, "stopSmsSyncOperations: Stopping all sync activities and cancelling job.")
        currentSyncJob?.cancel("Sync operations stopped explicitly")
        currentSyncJob = null
        updateNotification("SMS sync is disabled.")
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: Service destroying.")
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        currentSyncJob?.cancel("Service is being destroyed")
        serviceJob.cancel("Service is being destroyed")
        Log.i(TAG, "onDestroy: Preference change listener unregistered, jobs cancelled.")
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "SMS Sync Service Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply { description = "Channel for SMS Sync background operations" }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
            Log.d(TAG, "Notification channel created.")
        }
    }

    private fun createNotification(contentText: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, pendingIntentFlags)
        
        Log.d(TAG, "Creating notification with text: $contentText")
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SandeshVahak SMS Sync")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.cloud_arrow_up_solid) // Changed to existing icon
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setSilent(true)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .build()
    }

    private fun updateNotification(contentText: String) {
        if (isServiceManuallyStopped && !Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)){
            Log.d(TAG, "Not updating notification as service is manually stopped and sync disabled: $contentText")
            return
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, createNotification(contentText))
        Log.d(TAG, "Notification updated with text: $contentText")
    }
}
