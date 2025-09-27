package com.akslabs.SandeshVahak.ui

import android.Manifest
import android.app.Activity
import android.app.NotificationManager // Added import
import android.content.Context // Added import
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.akslabs.SandeshVahak.App // Import App class
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.debug.DatabaseDebugHelper
import com.akslabs.SandeshVahak.ui.main.MainPage
import com.akslabs.SandeshVahak.ui.main.MainViewModel
import com.akslabs.SandeshVahak.ui.main.nav.screenScopedViewModel
import com.akslabs.SandeshVahak.ui.onboarding.OnboardingPage
import com.akslabs.SandeshVahak.ui.permission.PermissionDialogScreen
import com.akslabs.SandeshVahak.ui.permission.PermissionViewModel
import com.akslabs.SandeshVahak.ui.theme.AppTheme
import com.akslabs.SandeshVahak.utils.NotificationHelper
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.SandeshVahak.services.SmsContentObserver
import com.akslabs.SandeshVahak.services.SmsObserverService
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val isSdkAbove33 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    private var hasSmsPerm by mutableStateOf(false)
    private var startDestination by mutableStateOf(ScreenFlow.Onboarding.route)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val allCoreServicesReady = withContext(Dispatchers.IO) {
                App.awaitCoreInitializations()
            }

            if (!allCoreServicesReady) {
                Log.e("MainActivity", "Core services failed to initialize in onCreate. Cannot proceed.")
                // Optionally, inform the user or finish the activity
                // finish() 
                return@launch // IMPORTANT: Stop execution if core services are not ready
            }

            // Core services are ready, proceed with MainActivity setup
            Log.d("MainActivity", "Core services ready. Proceeding with onCreate setup.")

            NotificationHelper.createNotificationChannels(this@MainActivity)
            DatabaseDebugHelper.debugDatabaseState(this@MainActivity) // Safe now
            WorkModule.DailyDatabaseBackup.enqueuePeriodic() // Safe now

            try {
                WorkModule.SmsSync.enqueueKeepAlive() // Safe now
            } catch (e: Exception) {
                Log.w("MainActivity", "Failed to enqueue keep-alive worker", e)
            }

            withContext(Dispatchers.Main) {
                hasSmsPerm = ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.READ_SMS
                ) == PackageManager.PERMISSION_GRANTED

                startDestination = when {
                    Preferences.getEncryptedLong(Preferences.channelId, 0) == 0L -> ScreenFlow.Onboarding.route
                    !hasSmsPerm -> ScreenFlow.Permission.route
                    else -> ScreenFlow.Main.route
                }
                Log.d("MainActivity", "Initial startDestination set to: $startDestination")

                setContent {
                    AppTheme {
                        val topNavController = rememberNavController()
                        NavHost(navController = topNavController, startDestination = startDestination) {
                            composable(ScreenFlow.Onboarding.route) {
                                OnboardingPage(onOnboardingComplete = {
                                    val navigateToRoute = if (hasSmsPerm) {
                                        ScreenFlow.Main.route
                                    } else {
                                        ScreenFlow.Permission.route
                                    }
                                    topNavController.navigate(navigateToRoute) {
                                        popUpTo(ScreenFlow.Onboarding.route) { inclusive = true }
                                    }
                                })
                            }
                            composable(ScreenFlow.Main.route) {
                                val viewModel: MainViewModel = screenScopedViewModel()
                                MainPage(viewModel)
                            }
                            dialog(ScreenFlow.Permission.route) {
                                val viewModel: PermissionViewModel = screenScopedViewModel()
                                val dialogQueue = viewModel.visiblePermissionDialogQueue
                                val permissionsToRequest = remember {
                                    arrayOf(
                                        Manifest.permission.READ_SMS,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    )
                                }
                                PermissionDialogScreen(
                                    permissionsToRequest = permissionsToRequest,
                                    onPermissionLauncherResult = { perms: Map<String, Boolean> ->
                                        permissionsToRequest.forEach { permission ->
                                            viewModel.onPermissionResult(
                                                permission,
                                                isGranted = perms[permission] == true
                                            )
                                        }
                                        perms[Manifest.permission.READ_SMS]?.let { isGranted ->
                                            hasSmsPerm = isGranted
                                            if (isGranted) {
                                                lifecycleScope.launch {
                                                    try {
                                                        com.akslabs.SandeshVahak.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                                                    } catch (_: Exception) {}
                                                }
                                                triggerInitialSmsReading()
                                            }
                                        }
                                    },
                                    dialogQueue = dialogQueue,
                                    isPermanentyDeclined = { permission ->
                                        !shouldShowRequestPermissionRationale(permission)
                                    },
                                    onGoToAppSettingsClick = { openAppSettings() },
                                    onDismissDialog = { viewModel.dismissDialog() },
                                    onOkClick = { viewModel.dismissDialog() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val allCoreServicesReady = withContext(Dispatchers.IO) {
                App.awaitCoreInitializations(timeoutMillis = 5000)
            }
            if (!allCoreServicesReady) {
                Log.e("MainActivity", "Core services not ready in onResume. Skipping onResume logic.")
                return@launch
            }

            Log.d("MainActivity", "Core services ready. Proceeding with onResume logic.")

            // --- New logic for starting service and dismissing notification ---
            val isSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (isSyncEnabled) {
                Log.i("MainActivity", "onResume: SMS Sync is enabled, ensuring SmsObserverService is started.")
                SmsObserverService.start(this@MainActivity)

                // Dismiss the "Reactivate Sync" notification
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(NotificationHelper.NOTIFICATION_ID_REACTIVATE_SYNC)
                Log.d("MainActivity", "onResume: Attempted to cancel NOTIFICATION_ID_REACTIVATE_SYNC.")
            } else {
                Log.i("MainActivity", "onResume: SMS Sync is disabled.")
            }
            // --- End of new logic ---

            withContext(Dispatchers.Main) {
                val currentHasSmsPerm = ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.READ_SMS
                ) == PackageManager.PERMISSION_GRANTED
                if (currentHasSmsPerm != hasSmsPerm) {
                    hasSmsPerm = currentHasSmsPerm
                }

                val newStartDestination = when {
                    Preferences.getEncryptedLong(Preferences.channelId, 0) == 0L -> ScreenFlow.Onboarding.route
                    !hasSmsPerm -> ScreenFlow.Permission.route
                    else -> ScreenFlow.Main.route
                }

                if (newStartDestination != startDestination) {
                    startDestination = newStartDestination
                    Log.d("MainActivity", "onResume: startDestination updated to: $startDestination. NavHost should recompose if key is used or NavController handles state.")
                }

                if (startDestination == ScreenFlow.Main.route) {
                    Preferences.edit { putString(Preferences.startTabKey, "") }
                }
            }

            if (hasSmsPerm) { // This block seems to be for initial SMS import if permissions were just granted. Keep as is.
                try {
                    val syncMode = Preferences.getString(Preferences.smsSyncModeKey, "ALL") ?: "ALL"
                    val baseline = Preferences.getLong(Preferences.smsSyncEnabledSinceKey, 0L)
                    val count = DbHolder.database.smsMessageDao().getAllCountFlow().first()

                    if (count == 0) {
                        if (syncMode == "NEW_ONLY" && baseline > 0L) {
                            Log.i("MainActivity", "NEW_ONLY mode: importing only messages after baseline $baseline")
                            com.akslabs.SandeshVahak.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                        } else {
                            Log.i("MainActivity", "ALL mode: importing all SMS messages")
                            com.akslabs.SandeshVahak.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                        }
                    } else {
                        com.akslabs.SandeshVahak.services.SmsReaderService.syncNewSmsToDatabase(this@MainActivity)
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error during SMS sync in onResume", e)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            val allCoreServicesReady = withContext(Dispatchers.IO) {
                App.awaitCoreInitializations(timeoutMillis = 5000)
            }
            if (!allCoreServicesReady) {
                Log.e("MainActivity", "Core services not ready in onStart. Skipping SmsContentObserver start.")
                return@launch
            }
            Log.d("MainActivity", "Core services ready. Proceeding with onStart logic.")
            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                SmsContentObserver.startObserving(this@MainActivity)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (App.coreServicesInitialized) { // Check the flag directly for onStop, as it's synchronous.
            Log.d("MainActivity", "Core services were initialized. Proceeding with onStop logic.")
            val smsSyncEnabled = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            if (!smsSyncEnabled) {
                Log.i("MainActivity", "onStop: SMS Sync is disabled, stopping SmsContentObserver.")
                SmsContentObserver.stopObserving(this)
            } else {
                Log.i("MainActivity", "onStop: SMS Sync is enabled, SmsContentObserver will continue running.")
            }
        } else {
            Log.w("MainActivity", "onStop: Core services not initialized (or check failed). Defaulting to stopping observer.")
            SmsContentObserver.stopObserving(this)
        }
    }

    private fun initializeSmsSync() {
        lifecycleScope.launch {
            if (!App.awaitCoreInitializations(5000)) { // This already has a timeout
                 Log.e("MainActivity", "initializeSmsSync: Core services not ready. Aborting.")
                return@launch
            }
            Log.d("MainActivity", "Core services ready. Proceeding with initializeSmsSync.")
            try {
                WorkModule.SmsSync.enqueue()
                if (hasSmsPerm) {
                    val serviceIntent = Intent(this@MainActivity, SmsObserverService::class.java)
                    startForegroundService(serviceIntent)
                }
                val lastSyncTime = Preferences.getLong("last_sms_sync_timestamp", 0L)
                val hoursSinceLastSync = (System.currentTimeMillis() - lastSyncTime) / (1000 * 60 * 60)
                if (lastSyncTime == 0L || hoursSinceLastSync > 6) {
                    WorkModule.SmsSync.enqueueOneTime()
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error initializing SMS sync", e)
            }
        }
    }

    private fun triggerInitialSmsReading() {
        lifecycleScope.launch {
             if (!App.awaitCoreInitializations(5000)) {
                 Log.e("MainActivity", "triggerInitialSmsReading: Core services not ready. Aborting.")
                return@launch
            }
            Log.i("MainActivity", "SMS permission granted; sync will be handled by settings/service after core init.")
        }
    }
}

sealed class ScreenFlow(val route: String) {
    data object Onboarding : ScreenFlow("onboard")
    data object Main : ScreenFlow("main")
    data object Permission : ScreenFlow("permission")
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}
