# Guide: Auto-Starting a Service on Boot in Android (SandeshVahak Example)

This document outlines the steps and components required to automatically start a service (specifically `SmsObserverService` in the SandeshVahak app) when an Android device boots up. It also covers ensuring related tasks like data synchronization continue to run reliably using `WorkManager`.

## Core Goal

The primary objective is to have `SmsObserverService` running shortly after device boot to monitor SMS messages, provided the user has enabled this feature in the app's settings.

## Overview of Components

1.  **`BootReceiver`**: A `BroadcastReceiver` that listens for the `android.intent.action.BOOT_COMPLETED` broadcast. This is the primary trigger to initiate post-boot operations.
2.  **`SmsObserverService`**: The main `Service` responsible for real-time SMS monitoring. It runs as a foreground service.
3.  **`WorkManager`**: Used for scheduling reliable background tasks:
    *   **`SmsSyncWorker`**: Performs the actual SMS data synchronization with the app's database/backend. It can be triggered for an immediate catch-up or run periodically.
    *   **`KeepAliveWorker`**: A periodic worker that currently shows a notification to prompt the user to open the app (which then starts the service) and ensures the main periodic sync task (`SmsSyncWorker`) remains scheduled.
    *   **`WorkModule.kt`**: A helper object to centralize `WorkManager` task definitions and enqueuing logic.
4.  **`NotificationHelper.kt`**: Manages creation of notification channels and display of various notifications, including one to prompt the user if the service needs to be (re)activated.
5.  **`Preferences.kt`**: Handles SharedPreferences for storing user settings (e.g., if sync is enabled).
6.  **`MainActivity.kt`**: The main UI of the app, responsible for user interactions with settings and for starting the service when the app is opened (especially after the user taps the notification from `KeepAliveWorker`).
7.  **`App.kt` (Application Class)**: For app-level initializations like `Preferences` and `WorkManager`.
8.  **`AndroidManifest.xml`**: Declares permissions, services, and receivers.

## Step-by-Step Implementation

### Step 1: Declare Permissions in `AndroidManifest.xml`

Your app needs permissions to receive the boot completed broadcast and run a foreground service. Add these to your `<manifest>` tag:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.akslabs.SandeshVahak">

    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_DATA_SYNC" /> <!-- For Android 14+ specific foreground service type -->

    <!-- For Android 13 (API 33) and above, if showing notifications -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>

    <!-- Other permissions like READ_SMS, INTERNET etc. as needed by your app -->
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> <!-- Often needed by WorkManager or sync logic -->

    <application
        ...>
    </application>
</manifest>
```

### Step 2: Declare `SmsObserverService` in `AndroidManifest.xml`

Declare your service within the `<application>` tag:

```xml
    <application
        ...>
        <service
            android:name="com.akslabs.SandeshVahak.services.SmsObserverService"
            android:exported="false"
            android:foregroundServiceType="dataSync" />
            <!-- Or other relevant foregroundServiceType like 'location', 'mediaPlayback', etc. -->

        <!-- MainActivity, other activities, BootReceiver, etc. -->
    </application>
```
*   `android:exported="false"`: The service is not meant to be started by other apps directly.
*   `android:foregroundServiceType="dataSync"`: Important for Android 10 (API 29) and above. Choose the type that best describes your service.

### Step 3: Declare `BootReceiver` in `AndroidManifest.xml`

Declare your `BroadcastReceiver` within the `<application>` tag:

```xml
    <application
        ...>
        <receiver
            android:name="com.akslabs.SandeshVahak.receivers.BootReceiver"
            android:enabled="true"
            android:exported="true">
            <!-- For Android N (API 24) and above, receivers registered in the manifest
                 for BOOT_COMPLETED do not receive the broadcast if the app is in a
                 direct boot-unaware state or if the app was force-stopped.
                 However, it's still the standard way to catch boot completion. -->
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <action android:name="android.intent.action.QUICKBOOT_POWERON" />
                <!-- Some devices use QUICKBOOT_POWERON -->
            </intent-filter>
        </receiver>

        <!-- Service, MainActivity, etc. -->
    </application>
```
*   `android:enabled="true"`: The receiver is enabled.
*   `android:exported="true"`: It needs to receive system broadcasts for `BOOT_COMPLETED`. (Before Android 7 (API 24), this also required `android:permission="android.permission.RECEIVE_BOOT_COMPLETED"` on the receiver tag itself, but this is no longer the recommended practice for `BOOT_COMPLETED` if the `<uses-permission>` is declared).

### Step 4: Implement `BootReceiver.kt`

This receiver checks app preferences and directly starts `SmsObserverService` if sync is enabled.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/receivers/BootReceiver.kt`
```kotlin
package com.akslabs.SandeshVahak.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "BootReceiver_DEBUG"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "IMMEDIATE onReceive ENTRY - Action: ${intent.action}")

        if (intent.action == Intent.ACTION_BOOT_COMPLETED ||
            intent.action == "android.intent.action.QUICKBOOT_POWERON") {
            Log.i(TAG, "Boot event received: ${intent.action}. Initializing post-boot tasks.")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Ensure Preferences are initialized (ideally in Application.onCreate)
                    // Preferences.init(context.applicationContext) // Uncomment if not done in App class

                    val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
                    // Example: Add a preference to allow users to disable auto-start if desired
                    val autoStartAllowed = Preferences.getBoolean("autoStartAfterBootEnabledKey", true)

                    Log.i(TAG, "Preference state: autoStartAllowed=$autoStartAllowed, isSmsSyncEnabled=$isSyncEnabled")

                    if (isSyncEnabled && autoStartAllowed) {
                        Log.i(TAG, "Sync conditions met. Proceeding to start SmsObserverService.")
                        try {
                            SmsObserverService.start(context.applicationContext)
                            Log.i(TAG, "SmsObserverService.start() called successfully from BootReceiver.")
                        } catch (e: Exception) {
                            Log.e(TAG, "Error starting SmsObserverService from BootReceiver", e)
                            // Consider a fallback, e.g., scheduling a worker to show a notification.
                        }
                    } else {
                        Log.i(TAG, "Sync conditions not met or auto-start disabled. Service not started by BootReceiver.")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error during BootReceiver's async operations", e)
                }
            }
        }
        Log.d(TAG, "onReceive END - Action: ${intent.action}")
    }
}
```

### Step 5: Implement `SmsObserverService.kt`

This service runs in the foreground, monitors SMS, and coordinates with `WorkManager`.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/services/SmsObserverService.kt`

Key responsibilities:
*   Starts as a foreground service with a persistent notification.
*   Registers an `SmsContentObserver` (not detailed here, but assumed to exist for SMS monitoring).
*   Manages its lifecycle based on preference changes.
*   When sync is enabled (either on start or preference change):
    *   Enqueues an immediate one-time `SmsSyncWorker` for catch-up using `WorkModule.SMS_SYNC_ONE_TIME_WORK`.
    *   Enqueues the main periodic `SmsSyncWorker` via `WorkModule.SmsSync.enqueue()`.
    *   Enqueues the periodic `KeepAliveWorker` via `WorkModule.SmsSync.enqueueKeepAlive()`.

*(The full code for `SmsObserverService.kt` is in your project. The critical part for `WorkManager` interaction in `updateSyncStateBasedOnPreferences()` should be similar to this after sync is enabled):*

```kotlin
// Inside SmsObserverService.kt's updateSyncStateBasedOnPreferences() when isSyncEnabled:

// 1. Enqueue One-Time Catch-up Sync
val workData = Data.Builder().putString(SmsSyncWorker.KEY_SYNC_MODE, SmsSyncWorker.SYNC_MODE_CATCH_UP).build()
val smsSyncWorkRequest = OneTimeWorkRequestBuilder<SmsSyncWorker>()
    .setInputData(workData)
    .setConstraints(constraints) // Define appropriate constraints
    .build()

Log.i(TAG, "updateSyncState: Enqueuing ONE-TIME SMS sync work. Unique work: ${WorkModule.SMS_SYNC_ONE_TIME_WORK}")
WorkManager.getInstance(applicationContext).enqueueUniqueWork(
    WorkModule.SMS_SYNC_ONE_TIME_WORK, // Distinct name for one-time work
    ExistingWorkPolicy.REPLACE, // Replace if one is already pending/running
    smsSyncWorkRequest
)

// 2. Enqueue Main Periodic Sync (delegated to WorkModule)
Log.i(TAG, "updateSyncState: Enqueuing PERIODIC SMS sync work via WorkModule.")
WorkModule.SmsSync.enqueue() // This schedules the recurring SmsSyncWorker

// 3. Enqueue KeepAlive Worker (delegated to WorkModule)
Log.i(TAG, "updateSyncState: Enqueuing KeepAlive worker via WorkModule.")
WorkModule.SmsSync.enqueueKeepAlive()

updateServiceNotification("SMS Sync active. Monitoring for changes.")
```

### Step 6: Implement `WorkManager` Workers and `WorkModule.kt`

#### `WorkModule.kt`
This object centralizes `WorkManager` configurations and enqueuing logic.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/workers/WorkModule.kt`
*(Refer to your project file for the full code. Ensure it defines distinct unique names like `SMS_SYNC_WORK` for the main periodic task, `SMS_SYNC_ONE_TIME_WORK` for on-demand tasks, and `SMS_SYNC_KEEP_ALIVE_WORK` for the keep-alive task.)*

Key methods in `WorkModule.SmsSync`:
*   `enqueue()`: Enqueues the main periodic `SmsSyncWorker` (e.g., every 6 hours) using `ExistingPeriodicWorkPolicy.KEEP` and `WorkModule.SMS_SYNC_WORK`.
*   `enqueueOneTime(syncMode: String)`: Enqueues an immediate `SmsSyncWorker` using `ExistingWorkPolicy.REPLACE` and `WorkModule.SMS_SYNC_ONE_TIME_WORK`.
*   `enqueueKeepAlive()`: Enqueues the periodic `KeepAliveWorker` (e.g., every 15-30 minutes) using `ExistingPeriodicWorkPolicy.KEEP` and `WorkModule.SMS_SYNC_KEEP_ALIVE_WORK`.

#### `SmsSyncWorker.kt`
Performs the actual SMS synchronization. Runs as a foreground worker if doing significant work.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/workers/SmsSyncWorker.kt`
```kotlin
package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.R // For notification icon
import com.akslabs.SandeshVahak.services.SmsReaderService // Your actual sync logic
import com.akslabs.SandeshVahak.utils.NotificationHelper // For foreground worker notification

class SmsSyncWorker(val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    companion object {
        const val KEY_SYNC_MODE = "sync_mode"
        const val SYNC_MODE_FULL = "FULL"
        const val SYNC_MODE_CATCH_UP = "CATCH_UP"
        private const val TAG = "SmsSyncWorker_DEBUG"
        private const val FOREGROUND_NOTIFICATION_ID = 1005 // Unique ID for this worker's notification
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        // Create and return ForegroundInfo instance.
        // This is called if the worker is expedited or runs for a long time.
        val notification = NotificationHelper.createSyncWorkerNotificationBuilder(
            appContext,
            "Syncing SMS messages..."
        ).build()
        return ForegroundInfo(FOREGROUND_NOTIFICATION_ID, notification)
    }

    override suspend fun doWork(): Result {
        val syncMode = inputData.getString(KEY_SYNC_MODE) ?: SYNC_MODE_CATCH_UP
        Log.i(TAG, "doWork: Starting SMS Sync. Mode: $syncMode")

        try {
            // Call setForeground to run as a foreground service if doing long work
            // setForeground(getForegroundInfo()) // Call this before starting intensive work

            when (syncMode) {
                SYNC_MODE_FULL -> {
                    Log.i(TAG, "Performing full SMS sync.")
                    // SmsReaderService.syncAllSmsToDatabase(applicationContext) // Your actual sync logic
                    // Example: kotlinx.coroutines.delay(10000) // Simulate work
                }
                SYNC_MODE_CATCH_UP -> {
                    Log.i(TAG, "Performing catch-up SMS sync.")
                    // SmsReaderService.syncNewSmsToDatabase(applicationContext) // Your actual sync logic
                    // Example: kotlinx.coroutines.delay(5000) // Simulate work
                }
            }
            Log.i(TAG, "SMS Sync work ($syncMode) finished successfully.")
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Error during SMS sync ($syncMode): ${e.message}", e)
            return Result.retry() // or Result.failure()
        }
    }
}
```

#### `KeepAliveWorker.kt`
This worker's role is to ensure critical periodic tasks (like `SmsSyncWorker`) remain scheduled and to prompt the user to open the app if the main service seems to be down.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/workers/KeepAliveWorker.kt`
```kotlin
package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.utils.NotificationHelper

class KeepAliveWorker(
    val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val TAG = "KeepAliveWorker_DEBUG"
    }

    override suspend fun doWork(): Result {
        Log.d(TAG, "KeepAliveWorker doWork() CALLED")
        try {
            // Preferences.init(applicationContext) // Ensure initialized, ideally in App.onCreate

            val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            Log.d(TAG, "KeepAliveWorker: SMS Sync enabled in Prefs: $isSyncEnabled")

            if (isSyncEnabled) {
                Log.i(TAG, "KeepAliveWorker: Sync is enabled. Ensuring periodic SmsSyncWorker is scheduled.")
                WorkModule.SmsSync.enqueue() // This enqueues the main periodic SmsSyncWorker

                // This notification prompts the user to open the app,
                // which can then ensure SmsObserverService is running.
                // This acts as a fallback if BootReceiver somehow failed or service was stopped.
                Log.i(TAG, "KeepAliveWorker: Showing 'Reactivate Sync' notification.")
                NotificationHelper.showReactivateSyncNotification(applicationContext)

            } else {
                Log.i(TAG, "KeepAliveWorker: SMS Sync is disabled. No action taken.")
            }
            
            Log.d(TAG, "KeepAliveWorker doWork() COMPLETED successfully.")
            return Result.success()
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error in KeepAliveWorker.doWork()", e)
            return Result.retry()
        }
    }
}
```

### Step 7: Implement `NotificationHelper.kt`

Manages notification channels and displays notifications.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/utils/NotificationHelper.kt`
*(Refer to your project file. Key methods include `createNotificationChannels` and `showReactivateSyncNotification`)*

```kotlin
package com.akslabs.SandeshVahak.utils

// ... imports ...
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.ui.MainActivity // To open app on notification tap

object NotificationHelper {
    // ... other channel IDs ...
    const val CHANNEL_ID_POST_BOOT = "PostBootNotificationChannel" // For user prompts
    const val NOTIFICATION_ID_REACTIVATE_SYNC = 1004 // For the KeepAliveWorker notification

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            
            // Channel for SmsObserverService's own foreground notification (created in the service itself)
            // Channel for SmsSyncWorker's foreground notification
            // ...

            // Channel for user prompts (e.g., from KeepAliveWorker)
            val postBootChannel = NotificationChannel(
                CHANNEL_ID_POST_BOOT,
                "App Status Notifications",
                NotificationManager.IMPORTANCE_HIGH 
            ).apply {
                description = "Notifications about app status or requiring user action."
            }
            notificationManager.createNotificationChannel(postBootChannel)
            Log.d("NotificationHelper", "Notification channel $CHANNEL_ID_POST_BOOT created/ensured.")
        }
    }

    fun showReactivateSyncNotification(context: Context) {
        // ... (permission check for POST_NOTIFICATIONS for API 33+) ...

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlags)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_POST_BOOT)
            .setSmallIcon(R.drawable.icon) // Replace with your app's icon
            .setContentTitle("Activate SMS Sync")
            .setContentText("Tap to open SandeshVahak and ensure SMS sync is active.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID_REACTIVATE_SYNC, notification)
        Log.i("NotificationHelper", "Reactivate sync notification shown.")
    }

    // For SmsSyncWorker foreground notification
    fun createSyncWorkerNotificationBuilder(context: Context, contentText: String): NotificationCompat.Builder {
        // ... (implementation to build notification for foreground worker) ...
        // Example:
        createNotificationChannels(context) // Ensure channel exists
        return NotificationCompat.Builder(context, CHANNEL_ID_POST_BOOT) // Or a dedicated worker channel
            .setContentTitle("SandeshVahak Sync")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.cloud_arrow_up_solid) // Worker's icon
            .setOngoing(true)
            .setAutoCancel(false)
    }
}
```

### Step 8: Implement `Preferences.kt`

Manages SharedPreferences for storing user settings.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/data/localdb/Preferences.kt`
*(A simplified example. Your implementation might be more complex, e.g., using encrypted preferences.)*

```kotlin
package com.akslabs.SandeshVahak.data.localdb

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    private const val PREFS_NAME = "SandeshVahakPrefs"
    private lateinit var sharedPreferences: SharedPreferences

    // --- Preference Keys ---
    const val isSmsSyncEnabledKey = "is_sms_sync_enabled"
    // Add other keys as needed, e.g.,
    // const val autoStartPermissionKey = "auto_start_after_boot_enabled"


    fun init(context: Context) {
        if (!::sharedPreferences.isInitialized) {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        if (!::sharedPreferences.isInitialized) throw IllegalStateException("Preferences not initialized.")
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        if (!::sharedPreferences.isInitialized) throw IllegalStateException("Preferences not initialized.")
        sharedPreferences.edit().putBoolean(key, value).apply()
    }
    
    // Add other getters/setters (getString, putString, etc.)
}
```

### Step 9: Modify `MainActivity.kt`

The main UI, responsible for starting the service when the app is opened, especially after a user taps a notification from `KeepAliveWorker`.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/ui/MainActivity.kt`
*(Key modifications are in `onResume()`)*

```kotlin
package com.akslabs.SandeshVahak.ui

// ... imports ...
import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.lifecycleScope
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService
import com.akslabs.SandeshVahak.utils.NotificationHelper
import androidx.activity.ComponentActivity // Ensure this or your base activity is imported
import kotlinx.coroutines.launch // Ensure this is imported

class MainActivity : ComponentActivity() { // Or your base activity class

    // ... (onCreate and other activity code) ...

    override fun onResume() {
        super.onResume()
        // ... (other onResume logic like permission checks, UI updates) ...

        // Logic to handle service start and notification dismissal
        lifecycleScope.launch { // Or appropriate scope
            // Ensure core services like Preferences are ready
            // App.awaitCoreInitializations() // If you have such a mechanism

            val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (isSyncEnabled) {
                Log.i("MainActivity", "onResume: SMS Sync is enabled, ensuring SmsObserverService is started.")
                SmsObserverService.start(this@MainActivity)

                // Dismiss the "Reactivate Sync" notification if it was shown
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(NotificationHelper.NOTIFICATION_ID_REACTIVATE_SYNC)
                Log.d("MainActivity", "onResume: Attempted to cancel NOTIFICATION_ID_REACTIVATE_SYNC.")
            } else {
                Log.i("MainActivity", "onResume: SMS Sync is disabled.")
            }
        }
    }
}
```

### Step 10: Implement `App.kt` (Application Class)

Initialize global components like `Preferences` and `WorkManager` here.

**Path**: `app/src/main/java/com/akslabs/SandeshVahak/App.kt`
```kotlin
package com.akslabs.SandeshVahak

import android.app.Application
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.SandeshVahak.utils.NotificationHelper // For creating channels early

class App : Application() {
    companion object {
        private const val TAG = "SandeshVahakApp"
        // Optional: for other parts of app to await core init
        // val coreServicesInitializedLatch = java.util.concurrent.CountDownLatch(1) 
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Application onCreate called")

        // Initialize Preferences
        try {
            Preferences.init(applicationContext)
            Log.d(TAG, "Preferences initialized successfully.")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Preferences.", e)
        }

        // Initialize WorkManager via WorkModule
        try {
            WorkModule.create(applicationContext)
            Log.d(TAG, "WorkModule initialized successfully.")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing WorkModule.", e)
        }
        
        // Create Notification Channels (can also be done in MainActivity or first component needing it)
        try {
            NotificationHelper.createNotificationChannels(applicationContext)
            Log.d(TAG, "Notification channels created/ensured.")
        } catch (e: Exception) {
            Log.e(TAG, "Error creating notification channels.", e)
        }
        
        // Example: Enqueue keep-alive on app start if not handled elsewhere initially
        // if (Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)) {
        //    WorkModule.SmsSync.enqueueKeepAlive()
        // }

        // coreServicesInitializedLatch.countDown() // If using a latch
        Log.i(TAG, "Application onCreate tasks completed.")
    }
}
```
And declare it in `AndroidManifest.xml`:

```xml
<application
    android:name=".App"
    ...>
    <!-- activities, services, receivers -->
</application>
```

### Step 11: `AndroidManifest.xml` - Final Review of Declarations

Ensure all these components are declared:
*   `<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />`
*   `<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />`
*   `<uses-permission android:name="android.permission.FOREGROUND_SERVICE_DATA_SYNC" />` (if applicable)
*   `<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>` (for API 33+)
*   Other necessary permissions for your app.
*   `<application android:name=".App" ...>`
*   `<service android:name=".services.SmsObserverService" ... />`
*   `<receiver android:name=".receivers.BootReceiver" ...>` (with intent filter for `BOOT_COMPLETED`)

## How It All Works Together (Flow Summary)

1.  **Device Boots**: The Android system broadcasts `android.intent.action.BOOT_COMPLETED`.
2.  **`BootReceiver` Triggered**:
    *   `BootReceiver.onReceive()` is called.
    *   It launches a coroutine to perform work off the main thread.
    *   It checks `Preferences` to see if SMS sync and auto-start are enabled.
    *   If yes, it calls `SmsObserverService.start(context)`.
3.  **`SmsObserverService` Lifecycle**:
    *   If started (by `BootReceiver` or `MainActivity`), it runs `onStartCommand()`.
    *   It starts itself as a foreground service with a notification.
    *   It calls `updateSyncStateBasedOnPreferences()`. This method:
        *   Enqueues an immediate `SmsSyncWorker` for catch-up (`WorkModule.SMS_SYNC_ONE_TIME_WORK`).
        *   Enqueues the main periodic `SmsSyncWorker` (`WorkModule.SmsSync.enqueue()`).
        *   Enqueues the periodic `KeepAliveWorker` (`WorkModule.SmsSync.enqueueKeepAlive()`).
        *   Starts its internal `SmsContentObserver` to monitor new SMS.
4.  **`KeepAliveWorker` Periodic Execution**:
    *   Runs periodically (e.g., every 15-30 minutes).
    *   Checks `Preferences`. If sync is enabled:
        *   It re-enqueues the main periodic `SmsSyncWorker` via `WorkModule.SmsSync.enqueue()`. (This is safe due to `ExistingPeriodicWorkPolicy.KEEP` and ensures the periodic sync is always scheduled).
        *   It calls `NotificationHelper.showReactivateSyncNotification()`. This notification serves as a user-facing fallback: if `BootReceiver` failed or the service was stopped for some reason, the user sees this prompt. Tapping it opens `MainActivity`.
5.  **`MainActivity` Interaction**:
    *   When `MainActivity`'s `onResume()` is called (e.g., user opens app, or taps the "Reactivate Sync" notification):
        *   It checks `Preferences`. If sync is enabled, it calls `SmsObserverService.start(this)`. This is safe and ensures the service is running if the app is in the foreground.
        *   It dismisses the `NOTIFICATION_ID_REACTIVATE_SYNC` notification shown by `KeepAliveWorker`.
6.  **`SmsSyncWorker` Execution**:
    *   The **one-time** worker runs soon after being enqueued by `SmsObserverService` for initial catch-up.
    *   The **periodic** worker runs at its scheduled interval (e.g., every 6 hours) to perform background syncs.

## Adapting for Other Services

To start a *different* service (e.g., `MyOtherService`) after boot:

1.  **Create Your Service**: Implement `MyOtherService.kt` similar to `SmsObserverService` if it needs to be a foreground service, or as a regular `Service` if its work is short-lived and can be handled by `IntentService` or a simple `Service` with its own threading.
2.  **Declare in Manifest**: Add `<service android:name=".services.MyOtherService" ... />` to `AndroidManifest.xml`.
3.  **Modify `BootReceiver`**:
    *   In `BootReceiver.onReceive()`, after checking relevant preferences for `MyOtherService`:
        ```kotlin
        if (isMyOtherServiceEnabled && isAutoStartAllowed) {
            Log.i(TAG, "Conditions met for MyOtherService. Starting it.")
            val serviceIntent = Intent(context.applicationContext, MyOtherService::class.java)
            // If it's a foreground service (Android 8+):
            ContextCompat.startForegroundService(context.applicationContext, serviceIntent)
            // Or for regular background service (pre-Android 8 or if not long-running):
            // context.applicationContext.startService(serviceIntent)
        }
        ```
4.  **`WorkManager` for Periodic Tasks**: If `MyOtherService` has tasks that need to run periodically even if the service itself isn't constantly running (or to support the service), create a dedicated `Worker` (e.g., `MyOtherTaskWorker`) and enqueue it from `MyOtherService` or `BootReceiver` using `WorkModule`, similar to how `SmsSyncWorker` is handled.
5.  **Preferences**: Add preferences keys in `Preferences.kt` specific to `MyOtherService` (e.g., `isMyOtherServiceEnabledKey`).
6.  **UI Interaction**: Update `MainActivity` or other relevant UI components to allow users to enable/disable `MyOtherService` and to potentially start it when the app is opened.

This comprehensive setup ensures that your service is initiated on boot and that its associated background tasks are reliably scheduled, with fallbacks involving user interaction through notifications.
