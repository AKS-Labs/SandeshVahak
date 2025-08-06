package com.akslabs.chitralaya

import android.app.Application

import com.akslabs.Suchak.api.BotApi
import com.akslabs.Suchak.data.localdb.DbHolder
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.Suchak.utils.coil.ImageLoaderModule
import com.akslabs.Suchak.utils.connectivity.ConnectivityObserver
import com.akslabs.Suchak.workers.WorkModule
import com.akslabs.chitralaya.utils.PerformanceMonitor


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Preferences.init(applicationContext)
        DbHolder.create(applicationContext)
        WorkModule.create(applicationContext)
        ImageLoaderModule.create(applicationContext)
        ConnectivityObserver.init(applicationContext)
        BotApi.create()

        // Start performance monitoring
        PerformanceMonitor.startPeriodicReporting(5) // Report every 5 minutes


    }
}