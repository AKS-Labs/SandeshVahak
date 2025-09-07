package com.akslabs.chitralaya.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.ContentObserver
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Telephony
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.chitralaya.ui.MainActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.coroutineContext

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
            context.stopService(intent) // Changed this line
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

    private var smsContentObserver: ContentObserver? = null

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: Service creating.")
        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
        createNotificationChannel()
        Log.i(TAG, "onCreate: Preference change listener registered.")
        updateSyncStateBasedOnPreferences(isInitialCall = true)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: Received action: ${intent?.action}, startId: $startId")
        when (intent?.action) {
            ACTION_START_SERVICE -> {
                isServiceManuallyStopped = false
                Log.i(TAG, "onStartCommand: ACTION_START_SERVICE. Updating sync state.")
                startForeground(NOTIFICATION_ID, createNotification("Initializing SMS Sync..."))
                updateSyncStateBasedOnPreferences()
            }
            ACTION_STOP_SERVICE -> {
                Log.i(TAG, "onStartCommand: ACTION_STOP_SERVICE. Stopping sync operations and service.")
                isServiceManuallyStopped = true
                stopSmsSyncOperations()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                return START_NOT_STICKY
            }
            else -> {
                 Log.w(TAG, "onStartCommand: Received unknown or null action. Starting service and checking prefs.")
                 startForeground(NOTIFICATION_ID, createNotification("Initializing SMS Sync..."))
                 updateSyncStateBasedOnPreferences()
            }
        }
        return START_STICKY
    }

    private fun updateSyncStateBasedOnPreferences(isInitialCall: Boolean = false) {
        val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
        val currentSyncMode = Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
        val syncEnabledSince = Preferences.getLong(Preferences.smsSyncEnabledSinceKey, 0L)

        Log.i(TAG, "updateSyncStateBasedOnPreferences: isEnabled=$isSyncEnabled, mode=$currentSyncMode, since=$syncEnabledSince, manuallyStopped=$isServiceManuallyStopped, isInitialCall=$isInitialCall")

        currentSyncJob?.cancel()

        if (isServiceManuallyStopped) {
            Log.i(TAG, "updateSyncStateBasedOnPreferences: Service was manually stopped. Not starting operations.")
            stopSmsSyncOperations()
            return
        }

        if (isSyncEnabled) {
            when (currentSyncMode) {
                Preferences.SMS_SYNC_MODE_ALL -> {
                    Log.i(TAG, "updateSyncStateBasedOnPreferences: Sync ALL mode detected. Starting full sync.")
                    // Notification will be updated within performFullSmsSync
                    currentSyncJob = serviceScope.launch {
                        performFullSmsSync()
                    }
                }
                Preferences.SMS_SYNC_MODE_NEW_ONLY -> {
                    Log.i(TAG, "updateSyncStateBasedOnPreferences: Sync NEW_ONLY mode detected. Starting catch-up and monitoring.")
                    startForeground(NOTIFICATION_ID, createNotification("Monitoring new SMS messages..."))
                    currentSyncJob = serviceScope.launch {
                        performCatchUpSyncAndMonitorNewSms(syncEnabledSince)
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
             if (!isInitialCall) {
                Log.i(TAG, "Stopping service as sync is disabled and not an initial call.")
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
    }

    private suspend fun performFullSmsSync() {
        Log.i(TAG, "performFullSmsSync: User selected 'Sync ALL'. Initiating force sync.")
        updateNotification("Starting full SMS sync...")

        var syncError: String? = null
        var totalMessagesProcessed = 0

        try {
            SmsSyncService.forceSync(applicationContext).collect { progress ->
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
        } catch (e: Exception) {
            Log.e(TAG, "Exception during forceSync collection", e)
            syncError = e.message ?: "Unknown error during sync"
            updateNotification("Full sync critically failed: ${syncError?.take(50)}")
        }

        if (syncError == null) {
            Log.i(TAG, "performFullSmsSync: Full sync process completed successfully ($totalMessagesProcessed messages). Switching to NEW_ONLY mode.")
            val newSyncEnabledSince = System.currentTimeMillis()
            Preferences.edit {
                putString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
                putLong(Preferences.smsSyncEnabledSinceKey, newSyncEnabledSince)
                // isSmsSyncEnabledKey should already be true, set when user selected "Sync All"
            }
            // Notification will be updated by the subsequent call to updateSyncStateBasedOnPreferences 
            // when the preference change listener fires and triggers NEW_ONLY mode.
            // Or, we can directly update it here to "Monitoring new messages."
            // Let's rely on the preference change listener for now to keep logic clean.
        } else {
            Log.e(TAG, "performFullSmsSync: Full sync encountered an error: $syncError. Not switching to NEW_ONLY mode automatically.")
            // The notification already reflects the error.
            // We might want to set isSmsSyncEnabledKey to false here to stop further attempts until user intervention,
            // or leave it in ALL mode for the user to retry.
            // For now, leaving prefs as they are, allowing user to retry or change mode.
        }
        Log.i(TAG, "performFullSmsSync: END")
    }

    private suspend fun performCatchUpSyncAndMonitorNewSms(syncEnabledSinceMillis: Long) {
        Log.i(TAG, "performCatchUpSyncAndMonitorNewSms: Starting with syncEnabledSince=$syncEnabledSinceMillis.")
        updateNotification("Monitoring new SMS messages...") // Initial notification for this mode

        // Perform an initial catch-up for any messages missed since syncEnabledSinceMillis
        // This is important if the service was stopped and restarted.
        Log.d(TAG, "Performing initial catch-up for NEW_ONLY mode.")
        try {
            val quickSyncResult = SmsSyncService.performQuickSync(applicationContext)
            when (quickSyncResult) {
                is SmsSyncResult.Success -> Log.i(TAG, "Initial catch-up sync: ${quickSyncResult.messagesSynced} messages.")
                is SmsSyncResult.Error -> Log.e(TAG, "Initial catch-up sync error: ${quickSyncResult.message}")
                is SmsSyncResult.NoChannelConfigured -> Log.w(TAG, "Initial catch-up: No channel configured.")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during initial catch-up sync", e)
        }

        Log.i(TAG, "performCatchUpSyncAndMonitorNewSms: Initial catch-up complete. Starting ContentObserver.")
        updateNotification("Monitoring new SMS messages.") // Re-affirm after catch-up

        if (smsContentObserver == null) {
            smsContentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
                override fun onChange(selfChange: Boolean, uri: Uri?) {
                    super.onChange(selfChange, uri)
                    Log.i(TAG, "SmsContentObserver onChange: Uri: $uri. New SMS detected (or other change).")
                    // The actual sync of new SMS is handled by InstantSmsSyncWorker triggered from SmsContentObserver (your existing one)
                    // This service (SmsObserverService) mainly manages the long-running state and full/catch-up syncs.
                    // However, we can also trigger a quick check here if needed, though it might be redundant
                    // with what your other SmsContentObserver is doing.
                    // For now, this log is sufficient for this observer.
                    // A better approach might be to have this service *be* the primary place that launches quick syncs
                    // based on its own observer, rather than relying on another one.
                    // But let's stick to the current architecture for now and ensure full sync works.
                }
            }
            try {
                contentResolver.registerContentObserver(Telephony.Sms.CONTENT_URI, true, smsContentObserver!!)
                Log.i(TAG, "SmsContentObserver registered successfully for service lifetime monitoring.")
            } catch (e: SecurityException) {
                Log.e(TAG, "Failed to register SmsContentObserver due to SecurityException. Ensure READ_SMS permission.", e)
                updateNotification("Error: SMS permission missing for monitoring.")
            }
        } else {
            Log.d(TAG, "SmsContentObserver already registered.")
        }

        // The primary role of this coroutine in NEW_ONLY mode is to keep the service alive
        // and potentially perform periodic checks if the ContentObserver is missed.
        // The actual new SMS detection and immediate sync is handled by your other SmsContentObserver + InstantSmsSyncWorker.
        while (coroutineContext.isActive) {
            delay(3_600_000) // Keep-alive, e.g., check every hour or so for sanity, though not strictly for new SMS.
            Log.d(TAG, "performCatchUpSyncAndMonitorNewSms: Still active (monitoring loop); periodic check (not for new SMS).")
        }
        Log.i(TAG, "performCatchUpSyncAndMonitorNewSms: END (coroutine ended/cancelled)")
    }

    private fun stopSmsSyncOperations() {
        Log.i(TAG, "stopSmsSyncOperations: Stopping all sync activities and unregistering observer.")
        currentSyncJob?.cancel()
        currentSyncJob = null
        smsContentObserver?.let {
            contentResolver.unregisterContentObserver(it)
            smsContentObserver = null
            Log.i(TAG, "SmsContentObserver unregistered.")
        }
        updateNotification("SMS sync is disabled.")
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: Service destroying.")
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        currentSyncJob?.cancel()
        serviceJob.cancel()
        smsContentObserver?.let {
            contentResolver.unregisterContentObserver(it)
            smsContentObserver = null
            Log.i(TAG, "SmsContentObserver unregistered in onDestroy.")
        }
        Log.i(TAG, "onDestroy: Preference change listener unregistered, jobs cancelled.")
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "SMS Sync Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
            Log.d(TAG, "Notification channel created.")
        }
    }

    private fun createNotification(contentText: String): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d(TAG, "Creating notification with text: $contentText")
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SandeshVahak SMS Sync")
            .setContentText(contentText)
            .setSmallIcon(android.R.drawable.ic_popup_sync) 
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setSilent(true)
            .build()
    }

    private fun updateNotification(contentText: String) {
        val notification = createNotification(contentText)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
        Log.d(TAG, "Notification updated with text: $contentText")
    }
}
