package com.akslabs.chitralaya.services

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import android.util.Log
import com.akslabs.SandeshVahak.workers.WorkModule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Content observer to monitor SMS changes and trigger sync
 */
class SmsContentObserver(
    private val context: Context,
    handler: Handler = Handler(Looper.getMainLooper())
) : ContentObserver(handler) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var lastChangeTime = 0L
    private val debounceDelay = 300L // Reduced to 300ms for faster response

    companion object {
        private const val TAG = "SmsContentObserver"
        private var instance: SmsContentObserver? = null

        fun getInstance(context: Context): SmsContentObserver {
            return instance ?: synchronized(this) {
                instance ?: SmsContentObserver(context.applicationContext).also { instance = it }
            }
        }

        fun startObserving(context: Context) {
            try {
                val observer = getInstance(context)
                val contentResolver = context.contentResolver

                Log.d(TAG, "Registering content observer on URI=${Telephony.Sms.CONTENT_URI}, notifyForDescendants=true, thread=${Thread.currentThread().name}")
                // Register observer for SMS content changes
                contentResolver.registerContentObserver(
                    Telephony.Sms.CONTENT_URI,
                    true,
                    observer
                )

                Log.i(TAG, "✅ SMS content observer started and registered")
            } catch (e: Exception) {
                Log.e(TAG, "❌ Failed to start SMS content observer", e)
            }
        }

        fun stopObserving(context: Context) {
            try {
                instance?.let { observer ->
                    context.contentResolver.unregisterContentObserver(observer)
                    instance = null
                    Log.i(TAG, "SMS content observer stopped")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to stop SMS content observer", e)
            }
        }
    }

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.d(TAG, "onChange(selfChange=$selfChange) called with no URI")
        handleSmsChange()
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.d(TAG, "onChange(selfChange=$selfChange, uri=$uri)")
        handleSmsChange()
    }

    private fun handleSmsChange() {
        val currentTime = System.currentTimeMillis()

        // Optimized debounce for faster response
        if (currentTime - lastChangeTime < debounceDelay) {
            Log.d(TAG, "⏱️ SMS change debounced (${currentTime - lastChangeTime}ms)")
            return
        }

        lastChangeTime = currentTime

        scope.launch {
            val startTime = System.currentTimeMillis()
            try {
                Log.i(TAG, "⚡ SMS content changed, triggering lightning-fast sync")

                // Reduced delay for faster response (200ms instead of 1000ms)
                delay(200)

                // Check if SMS sync is enabled with robust error handling
                val isEnabled = try {
                    val value = com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
                        com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
                        false
                    )
                    Log.d(TAG, "Preference isSmsSyncEnabledKey read: $value")
                    value
                } catch (e: Exception) {
                    Log.w(TAG, "Failed to read SMS sync preference, trying fallback", e)
                    try {
                        val prefs = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
                        val value = prefs.getBoolean("isSmsSyncEnabled", false)  // Use consistent key
                        Log.d(TAG, "Fallback preference read isSmsSyncEnabled=$value")
                        value
                    } catch (fallbackException: Exception) {
                        Log.e(TAG, "Complete failure reading sync preference, defaulting to false", fallbackException)
                        false
                    }
                }

                Log.d(TAG, "SMS sync enabled: $isEnabled")

                // Check sync mode for debugging
                val syncMode = try {
                    com.akslabs.SandeshVahak.data.localdb.Preferences.getString(
                        com.akslabs.SandeshVahak.data.localdb.Preferences.smsSyncModeKey,
                        "ALL"
                    )
                } catch (e: Exception) {
                    "ALL"
                }

                val baseline = try {
                    com.akslabs.SandeshVahak.data.localdb.Preferences.getLong(
                        com.akslabs.SandeshVahak.data.localdb.Preferences.smsSyncEnabledSinceKey,
                        0L
                    )
                } catch (e: Exception) {
                    0L
                }

                Log.d(TAG, "Content observer triggered - Mode: $syncMode, Baseline: $baseline, IsEnabled: $isEnabled")

                // For NEW_ONLY mode, verify baseline is set correctly
                if (syncMode == "NEW_ONLY" && baseline == 0L) {
                    Log.w(TAG, "⚠️ NEW_ONLY mode detected but baseline is 0! This will prevent NEW_ONLY sync from working.")
                } else if (syncMode == "NEW_ONLY" && baseline > 0L) {
                    Log.i(TAG, "✅ NEW_ONLY mode properly configured with baseline: $baseline (${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(java.util.Date(baseline))})")
                }

                // Always sync new SMS messages to local database (for Device screen)
                val newCount = SmsReaderService.syncNewSmsToDatabase(context)
                Log.i(TAG, "📱 DB update: inserted $newCount new messages into local database (incremental, mode: $syncMode)")

                if (newCount > 0) {
                    val processingTime = System.currentTimeMillis() - startTime
                    Log.i(TAG, "🚀 Found $newCount new SMS messages in ${processingTime}ms")

                    // Only trigger cloud sync if user enabled it
                    if (isEnabled) {
                        Log.i(TAG, "☁️ Triggering immediate cloud sync (Instant worker) since SMS sync is enabled (mode: $syncMode)")
                        // Add a small delay to ensure database operations complete first
                        kotlinx.coroutines.delay(500L)
                        WorkModule.SmsSync.enqueueInstant()
                        Log.d(TAG, "✅ Instant sync worker enqueued successfully")
                    } else {
                        Log.d(TAG, "⏸️ SMS sync disabled, skipping cloud sync")
                    }
                } else {
                    val processingTime = System.currentTimeMillis() - startTime
                    Log.d(TAG, "✅ No new SMS messages found (${processingTime}ms)")
                }

            } catch (e: Exception) {
                val processingTime = System.currentTimeMillis() - startTime
                Log.e(TAG, "❌ Error handling SMS content change after ${processingTime}ms", e)
            }
        }
    }
}
