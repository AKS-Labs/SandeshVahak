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
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.Suchak.debug.DatabaseDebugHelper
import com.akslabs.Suchak.ui.main.MainPage
import com.akslabs.Suchak.ui.main.MainViewModel
import com.akslabs.Suchak.ui.main.nav.screenScopedViewModel
import com.akslabs.Suchak.ui.onboarding.OnboardingPage
import com.akslabs.Suchak.ui.permission.PermissionDialogScreen
import com.akslabs.Suchak.ui.permission.PermissionViewModel
import com.akslabs.Suchak.ui.theme.AppTheme
import com.akslabs.Suchak.utils.NotificationHelper
import com.akslabs.Suchak.workers.WorkModule
import com.akslabs.chitralaya.services.SmsObserverService
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

        // Initialize SMS sync on app startup
        initializeSmsSync()

        // Start daily database backup
        WorkModule.DailyDatabaseBackup.enqueuePeriodic()

        setContent {
            AppTheme {
                val topNavController = rememberNavController()
                NavHost(navController = topNavController, startDestination = startDestination) {
                    composable(ScreenFlow.Onboarding.route) {
                        OnboardingPage(onProceed = {
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
    }

    /**
     * Initialize SMS sync workers for automatic background sync
     */
    private fun initializeSmsSync() {
        lifecycleScope.launch {
            try {
                // Start periodic SMS sync (every 6 hours)
                WorkModule.SmsSync.enqueue()

                // Start quick SMS sync (every 2 hours)
                WorkModule.SmsSyncQuick.enqueue()

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
                android.util.Log.i("MainActivity", "Triggering initial SMS reading...")

                // Start SMS observer service for real-time monitoring
                val serviceIntent = Intent(this@MainActivity, SmsObserverService::class.java)
                startForegroundService(serviceIntent)

                // Trigger immediate SMS sync to read all existing messages
                WorkModule.SmsSync.enqueueOneTime()

                android.util.Log.i("MainActivity", "Initial SMS reading triggered successfully")
            } catch (e: Exception) {
                android.util.Log.e("MainActivity", "Error triggering initial SMS reading", e)
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