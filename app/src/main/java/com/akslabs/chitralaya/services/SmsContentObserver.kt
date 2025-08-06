package com.akslabs.chitralaya.services

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Telephony
import android.util.Log
import com.akslabs.Suchak.workers.WorkModule
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
                
                // Register observer for SMS content changes
                contentResolver.registerContentObserver(
                    Telephony.Sms.CONTENT_URI,
                    true,
                    observer
                )
                
                Log.i(TAG, "SMS content observer started")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to start SMS content observer", e)
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
        handleSmsChange()
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.d(TAG, "SMS content changed: $uri")
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

                // Sync new SMS messages to local database
                val newCount = SmsReaderService.syncNewSmsToDatabase(context)

                if (newCount > 0) {
                    val processingTime = System.currentTimeMillis() - startTime
                    Log.i(TAG, "🚀 Found $newCount new SMS messages in ${processingTime}ms, triggering immediate sync")

                    // Trigger immediate SMS sync to Telegram
                    WorkModule.SmsSync.enqueueOneTime()
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
