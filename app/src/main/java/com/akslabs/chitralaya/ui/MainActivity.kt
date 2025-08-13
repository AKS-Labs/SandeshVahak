package com.akslabs.chitralaya.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
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
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.debug.DatabaseDebugHelper
import com.akslabs.SandeshVahak.ui.main.MainPage
import com.akslabs.SandeshVahak.ui.main.MainViewModel
import com.akslabs.SandeshVahak.ui.main.nav.screenScopedViewModel
import com.akslabs.SandeshVahak.ui.onboarding.OnboardingPage
import com.akslabs.SandeshVahak.ui.permission.PermissionDialogScreen
import com.akslabs.SandeshVahak.ui.permission.PermissionViewModel
import com.akslabs.SandeshVahak.ui.theme.AppTheme
import com.akslabs.Suchak.utils.NotificationHelper
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.chitralaya.services.SmsObserverService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val isSdkAbove33 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    private var hasSmsPerm by mutableStateOf(false)
    private var startDestination by mutableStateOf(ScreenFlow.Onboarding.route)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize notification channels
        NotificationHelper.createNotificationChannels(this)

        // Debug database state on app startup
        lifecycleScope.launch {
            DatabaseDebugHelper.debugDatabaseState(this@MainActivity)
        }

        // Do not auto-initialize SMS sync on startup; wait for user to enable via toggle

        // Start daily database backup
        WorkModule.DailyDatabaseBackup.enqueuePeriodic()

        // Ensure notification channels exist and keep-alive worker is scheduled
        try {
            com.akslabs.SandeshVahak.workers.WorkModule.SmsSync.enqueueKeepAlive()
        } catch (e: Exception) {
            android.util.Log.w("MainActivity", "Failed to enqueue keep-alive worker", e)
        }

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
                                        // Trigger initial SMS reading when permission is granted
                                        // Read existing device SMS into local DB so Device screen shows content even if sync is off
                                        lifecycleScope.launch {
                                            try {
                                                com.akslabs.chitralaya.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                                            } catch (_: Exception) {}
                                        }
                                        triggerInitialSmsReading()
                                    }
                                }
                            },
                            dialogQueue = dialogQueue,
                            isPermanentyDeclined = { permission ->
                                !shouldShowRequestPermissionRationale(
                                    permission
                                )
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

    override fun onResume() {
        super.onResume()
        hasSmsPerm = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.READ_SMS
        ) == PackageManager.PERMISSION_GRANTED

        startDestination = when {
            Preferences.getEncryptedLong(Preferences.channelId, 0) == 0L -> ScreenFlow.Onboarding.route
            !hasSmsPerm -> ScreenFlow.Permission.route
            else -> ScreenFlow.Main.route
        }

        // Always ensure we're on the main screen when app is opened
        if (startDestination == ScreenFlow.Main.route) {
            // Reset any saved tab preference to ensure Device screen is shown
            Preferences.edit { 
                putString(Preferences.startTabKey, "") 
            }
        }

        // Keep Device screen populated from device SMS provider regardless of sync toggle
        if (hasSmsPerm) {
            lifecycleScope.launch {
                try {
                    // Check sync mode to determine how to populate the database
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

                    val count = com.akslabs.SandeshVahak.data.localdb.DbHolder.database
                        .smsMessageDao()
                        .getAllCountFlow()
                        .first()

                    if (count == 0) {
                        // Database is empty - populate based on sync mode
                        if (syncMode == "NEW_ONLY" && baseline > 0L) {
                            // NEW_ONLY mode: only import messages after baseline
                            android.util.Log.i("MainActivity", "NEW_ONLY mode: importing only messages after baseline $baseline")
                            com.akslabs.chitralaya.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                        } else {
                            // ALL mode or no baseline: import all messages
                            android.util.Log.i("MainActivity", "ALL mode: importing all SMS messages")
                            com.akslabs.chitralaya.services.SmsReaderService.syncAllSmsToDatabase(this@MainActivity)
                        }
                    } else {
                        // Database has messages - just sync new ones
                        com.akslabs.chitralaya.services.SmsReaderService.syncNewSmsToDatabase(this@MainActivity)
                    }
                } catch (_: Exception) {}
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // While app is visible, observe SMS changes to keep Device tab live, independent of cloud sync
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            try { com.akslabs.SandeshVahak.data.localdb.Preferences.init(applicationContext) } catch (_: Exception) {}
            com.akslabs.chitralaya.services.SmsContentObserver.startObserving(this)
        }
    }

    override fun onStop() {
        super.onStop()
        // Stop observing when app goes to background (cloud sync observer is handled by service when enabled)
        com.akslabs.chitralaya.services.SmsContentObserver.stopObserving(this)
    }

    /**
     * Initialize SMS sync workers for automatic background sync
     */
    private fun initializeSmsSync() {
        lifecycleScope.launch {
            try {
                // Start periodic SMS sync (every 6 hours)
                WorkModule.SmsSync.enqueue()

                // SMS sync is handled by SmsObserverService

                // Start SMS observer service for real-time monitoring
                if (hasSmsPerm) {
                    val serviceIntent = Intent(this@MainActivity, SmsObserverService::class.java)
                    startForegroundService(serviceIntent)
                }

                // Trigger an immediate one-time sync if this is a fresh install
                // or if it's been a while since last sync
                val lastSyncTime = Preferences.getLong("last_sms_sync_timestamp", 0L)
                val hoursSinceLastSync = (System.currentTimeMillis() - lastSyncTime) / (1000 * 60 * 60)

                if (lastSyncTime == 0L || hoursSinceLastSync > 6) {
                    // Trigger immediate sync for new installs or if it's been more than 6 hours
                    WorkModule.SmsSync.enqueueOneTime()
                }

            } catch (e: Exception) {
                // Log error but don't crash the app
                android.util.Log.e("MainActivity", "Error initializing SMS sync", e)
            }
        }
    }

    /**
     * Trigger initial SMS reading when permission is first granted
     */
    private fun triggerInitialSmsReading() {
        lifecycleScope.launch {
            try {
                android.util.Log.i("MainActivity", "SMS permission granted; waiting for user to enable sync")
                // Do not start observer or sync automatically; user will enable via toggle
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Error after SMS permission grant", e)
            }
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