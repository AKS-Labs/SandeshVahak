package com.akslabs.chitralaya

import android.app.Application

import com.akslabs.SandeshVahak.api.BotApi
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences

import com.akslabs.SandeshVahak.utils.connectivity.ConnectivityObserver
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.chitralaya.utils.PerformanceMonitor


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Preferences.init(applicationContext)
        DbHolder.create(applicationContext)
        WorkModule.create(applicationContext)

        ConnectivityObserver.init(applicationContext)
        BotApi.create()

        // Start performance monitoring
        PerformanceMonitor.startPeriodicReporting(5) // Report every 5 minutes


    }
}