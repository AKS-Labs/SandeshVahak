package com.akslabs.SandeshVahak // Ensuring package name from your file

import android.app.Application
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.api.BotApi
import com.akslabs.SandeshVahak.utils.connectivity.ConnectivityObserver
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.SandeshVahak.utils.PerformanceMonitor
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class App : Application() {

    companion object {
        private const val TAG = "SandeshVahakApp" // Matches your log tag
        private val coreInitLatch = CountDownLatch(1) // For other background tasks
        var coreServicesInitialized = false // Represents completion of background tasks
            private set

        fun awaitCoreInitializations(timeoutMillis: Long = 30000): Boolean {
            Log.d(TAG, "awaitCoreInitializations: Waiting for background core services...")
            return try {
                if (coreInitLatch.await(timeoutMillis, TimeUnit.MILLISECONDS)) {
                    Log.d(TAG, "awaitCoreInitializations: Background core services signaled ready.")
                    true
                } else {
                    Log.e(TAG, "awaitCoreInitializations: Timeout waiting for background core services.")
                    false
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                Log.e(TAG, "awaitCoreInitializations: Interrupted while waiting for background core services.", e)
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Application onCreate called")

        // Initialize WorkModule synchronously on the main thread
        // as it's critical for services like SmsObserverService that might start early.
        try {
            WorkModule.create(applicationContext)
            Log.d(TAG, "WorkModule initialized successfully (main thread)")
        } catch (e: Exception) {
            Log.e(TAG, "CRITICAL: Error during WorkModule initialization (main thread)", e)
            // Consider if your app can run without WorkManager. If not, this might be a fatal error.
        }

        // Continue with other, potentially longer, initializations on a background thread
        Thread {
            Log.d(TAG, "Core background initialization thread started.")
            var backgroundInitSucceeded = true
            try {
                Preferences.init(applicationContext)
                Log.d(TAG, "Preferences initialized successfully (background)")

                DbHolder.create(applicationContext)
                Log.d(TAG, "DbHolder.create() called (background)")

                BotApi.create()
                Log.d(TAG, "BotApi.create() called (background)")

                ConnectivityObserver.init(applicationContext)
                Log.d(TAG, "ConnectivityObserver initialized successfully (background)")

                PerformanceMonitor.startPeriodicReporting(5)
                Log.d(TAG, "Performance monitoring started (background)")

            } catch (e: Exception) {
                Log.e(TAG, "Error during core background initializations", e)
                backgroundInitSucceeded = false
            } finally {
                if (backgroundInitSucceeded) {
                    coreServicesInitialized = true // Mark background initializations as complete
                    Log.i(TAG, "All core background initializations completed successfully.")
                } else {
                    Log.w(TAG, "Core background initializations finished, but one or more failed.")
                }
                coreInitLatch.countDown() // Signal completion of background tasks
                Log.d(TAG, "Core background initialization thread finished, latch counted down.")
            }
        }.start()

        Log.i(TAG, "Application main thread onCreate tasks (excluding background init logic) completed.")
    }
}
