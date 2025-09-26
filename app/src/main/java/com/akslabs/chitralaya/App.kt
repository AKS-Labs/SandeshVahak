package com.akslabs.SandeshVahak

import android.app.Application
import android.util.Log

import com.akslabs.SandeshVahak.api.BotApi // Corrected import
import com.akslabs.SandeshVahak.data.localdb.DbHolder // Corrected import
import com.akslabs.SandeshVahak.data.localdb.Preferences

import com.akslabs.SandeshVahak.utils.connectivity.ConnectivityObserver // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule // Corrected import
import com.akslabs.SandeshVahak.services.SmsContentObserver
import com.akslabs.SandeshVahak.utils.PerformanceMonitor


class App : Application() {

    companion object {
        private const val TAG = "SandeshVahakApp"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Application onCreate called")

        try {
            Preferences.init(applicationContext)
            Log.d(TAG, "Preferences initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Preferences", e)
        }

        try {
            DbHolder.create(applicationContext)
            Log.d(TAG, "Database initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Database", e)
        }

        try {
            WorkModule.create(applicationContext)
            Log.d(TAG, "WorkManager initialized successfully")
            
            // Ensure keep-alive worker is scheduled
            WorkModule.SmsSync.enqueueKeepAlive()
            Log.d(TAG, "Keep-alive worker scheduled")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize WorkManager", e)
        }

        try {
            ConnectivityObserver.init(applicationContext)
            Log.d(TAG, "ConnectivityObserver initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize ConnectivityObserver", e)
        }

        try {
            BotApi.create()
            Log.d(TAG, "BotApi initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize BotApi", e)
        }

        // Start SmsContentObserver if sync is already enabled
        try {
            if (Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)) {
                Log.i(TAG, "SMS Sync is enabled, starting SmsContentObserver from Application class.")
                SmsContentObserver.startObserving(applicationContext)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to conditionally start SmsContentObserver", e)
        }

        // Start performance monitoring
        try {
            PerformanceMonitor.startPeriodicReporting(5) // Report every 5 minutes
            Log.d(TAG, "Performance monitoring started")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start performance monitoring", e)
        }

        Log.i(TAG, "Application initialization completed")
    }
}