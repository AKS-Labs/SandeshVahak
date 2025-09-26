package com.akslabs.SandeshVahak.ui.main.screens.settings

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.NetworkType // Still used by Auto Export DB
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences // Corrected import
import com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper

import com.akslabs.SandeshVahak.ui.components.SettingsListItem
import com.akslabs.SandeshVahak.ui.components.SettingsListItemWithSwitch
import com.akslabs.SandeshVahak.ui.components.SettingsListItemWithDialog
import com.akslabs.SandeshVahak.ui.components.SettingsSectionHeader
import com.akslabs.SandeshVahak.ui.components.SettingsSectionDivider
import com.akslabs.SandeshVahak.utils.connectivity.ConnectivityObserver
import com.akslabs.SandeshVahak.utils.connectivity.ConnectivityStatus
import com.akslabs.SandeshVahak.utils.toastFromMainThread
import com.akslabs.SandeshVahak.workers.WorkModule
import android.os.PowerManager
import android.provider.Settings as AndroidSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // Removed isSmsSyncEnabled and currentSmsSyncMode states as controls are removed

    var isAutoExportDatabaseEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoExportDatabaseEnabledKey, false)
        )
    }

    val totalCloudSmsCount by DbHolder.database.remoteSmsMessageDao().getTotalCountFlow()
        .collectAsStateWithLifecycle(initialValue = 0)

    val exportBackupFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument(BackupHelper.JSON_MIME)
    ) { uri ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                BackupHelper.exportDatabase(it, context)
            }
        }
    }

    val autoExportBackupFileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument(BackupHelper.JSON_MIME)
    ) { uri ->
        uri?.let {
            context.contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            Preferences.edit {
                putString(Preferences.autoExportDatabseLocation, it.toString())
            }
            WorkModule.PeriodicDbExport.enqueue()
            scope.launch {
                context.toastFromMainThread(
                    context.getString(R.string.periodic_database_export_enabled)
                )
            }
        }
    }

    val importPhotosBackupFile = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                BackupHelper.importDatabase(it, context)
            }
        }
    }

    val intervals = remember {
        listOf(
            "Daily" to 1,
            "Weekly" to 7,
            "Biweekly" to 14,
            "Monthly" to 30
        )
    }

    // networkTypes is still used for Auto Export DB, so it remains.
    val networkTypes = remember {
        listOf(
            "All networks" to NetworkType.CONNECTED,
            "Unmetered" to NetworkType.UNMETERED,
            "Metered" to NetworkType.METERED,
            "Not Roaming" to NetworkType.NOT_ROAMING
        )
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Removed "SMS Sync Settings" section header and its items

        // Privacy & Security Section - Cloud & Sync
        SettingsSectionHeader(title = "Privacy & Security")

        CloudSmsItem(totalCloudSmsCount = totalCloudSmsCount)

        BatteryOptimizationItem()

        AutoStartOnBootItem()

        SettingsListItem(
            title = stringResource(R.string.restore_all_from_cloud),
            subtitle = stringResource(R.string.sms_not_found_on_this_device, totalCloudSmsCount.toString()),
            icon = Icons.Outlined.CloudDownload,
            onClick = {
                scope.launch {
                    context.toastFromMainThread(
                        "To restore all SMS, use the 'Sync: ON' button in the Top Bar and select 'Sync all existing and new messages'."
                    )
                }
            }
        )

        SettingsSectionDivider()

        // Look & Feel Section - Import/Export
        SettingsSectionHeader(title = "Local Database Management")

        SettingsListItem(
            title = stringResource(R.string.import_database),
            subtitle = "Import photos database from file",
            icon = Icons.Rounded.MoveToInbox,
            onClick = {
                importPhotosBackupFile.launch(arrayOf(BackupHelper.JSON_MIME))
            }
        )

        SettingsListItem(
            title = stringResource(R.string.export_database),
            subtitle = "Export photos database to file",
            icon = Icons.Rounded.Outbox,
            onClick = {
                exportBackupFileLauncher.launch(
                    context.getString(R.string.SandeshVahak_sms_backup_json)
                )
            }
        )

        SettingsListItemWithSwitch(
            title = stringResource(R.string.auto_export_database),
            subtitle = "Automatically export database periodically",
            icon = Icons.Rounded.AutoMode,
            isChecked = isAutoExportDatabaseEnabled,
            onCheckedChange = { checked ->
                isAutoExportDatabaseEnabled = checked
                Preferences.edit {
                    putBoolean(Preferences.isAutoExportDatabaseEnabledKey, checked)
                }
                if (checked) {
                    autoExportBackupFileLauncher.launch(
                        context.getString(R.string.SandeshVahak_auto_sms_backup_json)
                    )
                } else {
                    WorkModule.PeriodicDbExport.cancel()
                    scope.launch {
                        context.toastFromMainThread(
                            context.getString(R.string.periodic_database_export_cancelled)
                        )
                    }
                }
            }
        )

        SettingsListItemWithDialog(
            title = stringResource(R.string.auto_export_interval),
            subtitle = "How often to export database",
            icon = Icons.Rounded.AccessTime,
            currentValue = intervals.drop(1).find {
                it.second.toString() == Preferences.getString(
                    Preferences.autoExportDatabaseIntervalKey,
                    Preferences.defaultAutoExportDatabaseIntervalKey.toString()
                )
            }?.first ?: "Weekly",
            entries = intervals.drop(1).map { it.first },
            values = intervals.drop(1).map { it.second.toString() },
            onValueChange = { value ->
                Preferences.edit {
                    putString(Preferences.autoExportDatabaseIntervalKey, value)
                }
                WorkModule.PeriodicDbExport.enqueue(forceUpdate = true)
            },
            enabled = isAutoExportDatabaseEnabled
        )
         // Removed NetworkType dialog for SMS sync, but Auto Export DB might need its own if it had one.
         // Assuming auto-export uses a default or doesn't have a UI for network type for now.

        SettingsSectionDivider()

        // Memory & Storage Section - Database Management
        SettingsSectionHeader(title = "Cloud Database")

        DatabaseBackupItem()

        SettingsSectionDivider()

        // Debugging Section
        SettingsSectionHeader(title = "Debugging")
        SettingsListItem(
            title = "Tools for debugging issues",
            subtitle = "Access debug tools and diagnostic information",
            icon = Icons.Rounded.BugReport,
            onClick = {
                scope.launch {
                    context.toastFromMainThread("Debug tools - Coming soon")
                }
            }
        )
        DatabaseStatusItem()
        SettingsSectionDivider()

        // About Section
        SettingsSectionHeader(title = "About")

        AboutAppItem()

        AboutSourceCodeItem()

        AboutLicenseItem()

        AboutContributorsItem()

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun CloudSmsItem(
    totalCloudSmsCount: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val syncSummaryText = if (totalCloudSmsCount > 0) {
        "$totalCloudSmsCount SMS messages synced to cloud"
    } else {
        "No SMS messages synced yet. Use the Sync button in the Top Bar to start."
    }

    SettingsListItem(
        title = "Cloud SMS Status",
        subtitle = syncSummaryText,
        icon = Icons.Rounded.CloudSync,
        modifier = modifier,
        onClick = {
            scope.launch {
                context.toastFromMainThread("SMS sync is controlled by the Sync button in the Top Bar.")
            }
        }
    )
}

@Composable
private fun DatabaseBackupItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var backupStats by remember { mutableStateOf<com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.BackupStats?>(null) }

    LaunchedEffect(Unit) {
        backupStats = com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.getBackupStats()
    }

    SettingsListItem(
        title = "Backup Database to Telegram",
        subtitle = if (backupStats?.isUpToDate == true) {
            "✅ Backup is up to date (${backupStats?.lastBackupFilename})"
        } else {
            "Upload complete database to Telegram for safekeeping"
        },
        icon = Icons.Rounded.CloudUpload,
        modifier = modifier,
        onClick = {
            scope.launch {
                val connectivityStatus = ConnectivityObserver.status()
                if (connectivityStatus != ConnectivityStatus.Available) {
                    context.toastFromMainThread("No internet connection. Please check your connection and try again.")
                    return@launch
                }

                try {
                    context.toastFromMainThread("Uploading database to Telegram...")
                    val result = com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.uploadDatabaseToTelegram(context)
                    result.fold(
                        onSuccess = { message ->
                            context.toastFromMainThread("✅ $message")
                            backupStats = com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.getBackupStats()
                        },
                        onFailure = { error ->
                            context.toastFromMainThread("❌ Backup failed: ${error.message}")
                        }
                    )
                } catch (e: Exception) {
                    context.toastFromMainThread("❌ Error: ${e.message}")
                }
            }
        }
    )
}

@Composable
private fun DatabaseStatusItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    SettingsListItem(
        title = "View Database Status",
        subtitle = "View current database and backup information",
        icon = Icons.Rounded.Info,
        modifier = modifier,
        onClick = {
            scope.launch {
                val stats = com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.getBackupStats()
                val message = buildString {
                    appendLine("📊 Database Status:")
                    appendLine("• SMS Messages: ${stats.currentSmsMessages}")
                    appendLine("• Remote SMS Messages: ${stats.currentRemoteSmsMessages}")
                    appendLine()
                    if (stats.lastBackupTime > 0) {
                        appendLine("☁️ Last Backup:")
                        appendLine("• File: ${stats.lastBackupFilename}")
                        appendLine("• Time: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault()).format(java.util.Date(stats.lastBackupTime))}")
                        appendLine("• Status: ${if (stats.isUpToDate) "✅ Up to date" else "⚠️ Needs backup"}")
                    } else {
                        appendLine("☁️ No backup found")
                    }
                }
                android.util.Log.i("DatabaseStatus", message)
                context.toastFromMainThread("Database status logged - check logcat")
            }
        }
    )
}

@Composable
private fun AboutAppItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    SettingsListItem(
        title = "Version",
        subtitle = try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            "${packageInfo.versionName} (${packageInfo.longVersionCode})"
        } catch (e: Exception) {
            "Unknown version"
        },
        icon = Icons.Rounded.Info,
        modifier = modifier,
        onClick = { /* No action for version info */ }
    )
}

@Composable
private fun AboutSourceCodeItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    SettingsListItem(
        title = "Source Code",
        subtitle = "View the project source code on GitHub",
        icon = Icons.Rounded.Code,
        modifier = modifier,
        onClick = {
            try {
                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse("https://github.com/AKS-Labs/SandeshVahak")
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                scope.launch {
                    context.toastFromMainThread("Unable to open link")
                }
            }
        }
    )
}

@Composable
private fun AboutLicenseItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    SettingsListItem(
        title = "License",
        subtitle = "View the app license information",
        icon = Icons.Rounded.Gavel,
        modifier = modifier,
        onClick = {
            try {
                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse("https.github.com/AKS-Labs/SandeshVahak#Apache-2.0-1-ov-file")
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                scope.launch {
                    context.toastFromMainThread("Unable to open link")
                }
            }
        }
    )
}

@Composable
private fun AboutContributorsItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    SettingsListItem(
        title = "Contributors",
        subtitle = "See all contributors on GitHub",
        icon = Icons.Rounded.People,
        modifier = modifier,
        onClick = {
            try {
                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse("https://github.com/AKS-Labs/SandeshVahak")
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                scope.launch {
                    context.toastFromMainThread("Unable to open link")
                }
            }
        }
    )
}

@Composable
private fun BatteryOptimizationItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val pm = context.getSystemService(android.content.Context.POWER_SERVICE) as PowerManager
    val packageName = context.packageName
    val isIgnoring = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        pm.isIgnoringBatteryOptimizations(packageName)
    } else true

    var isRequested by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isBatteryOptimizationExemptionRequestedKey, false)
        )
    }

    val currentSubtitle = when {
        isIgnoring -> "Already exempted from battery optimizations"
        isRequested -> "Requested; please grant exemption in settings"
        else -> stringResource(id = R.string.battery_optimization_exemption_desc)
    }

    SettingsListItem(
        title = stringResource(id = R.string.battery_optimization_exemption),
        subtitle = currentSubtitle,
        icon = Icons.Rounded.BatterySaver,
        modifier = modifier,
        onClick = {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && !isIgnoring) {
                try {
                    val intent = Intent(AndroidSettings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
                        data = android.net.Uri.parse("package:$packageName")
                    }
                    context.startActivity(intent)
                    Preferences.edit { putBoolean(Preferences.isBatteryOptimizationExemptionRequestedKey, true) }
                    isRequested = true
                } catch (e: Exception) {
                    scope.launch {
                        context.toastFromMainThread("Unable to open battery optimization settings")
                    }
                }
            } else {
                scope.launch {
                    context.toastFromMainThread("Battery optimization already exempted")
                }
            }
        }
    )
}

@Composable
private fun AutoStartOnBootItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoStartOnBootEnabledKey, true)
        )
    }

    SettingsListItemWithSwitch(
        title = stringResource(id = R.string.auto_start_on_boot),
        subtitle = stringResource(id = R.string.auto_start_on_boot_desc),
        icon = Icons.Rounded.RestartAlt,
        isChecked = isEnabled,
        onCheckedChange = { checked ->
            isEnabled = checked
            Preferences.edit { putBoolean(Preferences.isAutoStartOnBootEnabledKey, checked) }
            if (checked) {
                scope.launch {
                    context.toastFromMainThread("Auto-start enabled. Some devices require enabling auto-start in system settings for background startup.")
                }
            }
        },
        modifier = modifier
    )
}
