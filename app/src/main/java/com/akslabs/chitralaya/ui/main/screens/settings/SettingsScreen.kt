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
import androidx.work.NetworkType
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.Preferences
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // State for settings
    var isAutoSmsBackupEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoBackupEnabledKey, false)
        )
    }

    var isAutoExportDatabaseEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoExportDatabaseEnabledKey, false)
        )
    }

    val totalCloudSmsCount by DbHolder.database.remoteSmsMessageDao().getTotalCountFlow()
        .collectAsStateWithLifecycle(initialValue = 0)



    // File launchers
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

    // Interval and network type options
    val intervals = remember {
        listOf(
            "Daily" to 1,
            "Weekly" to 7,
            "Biweekly" to 14,
            "Monthly" to 30
        )
    }

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

        // General Section - Backup & Sync Settings
        SettingsSectionHeader(title = "General")

        SettingsListItemWithSwitch(
            title = stringResource(R.string.auto_periodic_backup),
            subtitle = "Automatically backup SMS to cloud",
            icon = Icons.Rounded.CloudSync,
            isChecked = isAutoSmsBackupEnabled,
            onCheckedChange = { checked ->
                isAutoSmsBackupEnabled = checked
                Preferences.edit {
                    putBoolean(Preferences.isAutoBackupEnabledKey, checked)
                }
                if (checked) {
                    // SMS backup is handled automatically by SmsObserverService
                    scope.launch {
                        context.toastFromMainThread(context.getString(R.string.periodic_backup_enabled))
                    }
                } else {
                    // SMS backup is handled automatically by SmsObserverService
                    scope.launch {
                        context.toastFromMainThread(context.getString(R.string.periodic_backup_cancelled))
                    }
                }
            }
        )

        SettingsListItemWithDialog(
            title = stringResource(R.string.backup_interval),
            subtitle = "How often to backup",
            icon = Icons.Rounded.AccessTime,
            currentValue = intervals.find {
                it.second.toString() == Preferences.getString(
                    Preferences.autoBackupIntervalKey,
                    Preferences.defaultAutoBackupInterval.toString()
                )
            }?.first ?: "Weekly",
            entries = intervals.map { it.first },
            values = intervals.map { it.second.toString() },
            onValueChange = { value ->
                Preferences.edit {
                    putString(Preferences.autoBackupIntervalKey, value)
                }
                // SMS backup is handled automatically by SmsObserverService
            },
            enabled = isAutoSmsBackupEnabled
        )

        SettingsListItemWithDialog(
            title = stringResource(R.string.backup_network_type),
            subtitle = "Network preference for backup",
            icon = Icons.Rounded.SignalCellularAlt,
            currentValue = networkTypes.find {
                it.second.name == Preferences.getString(
                    Preferences.autoBackupNetworkTypeKey,
                    NetworkType.CONNECTED.name
                )
            }?.first ?: "All networks",
            entries = networkTypes.map { it.first },
            values = networkTypes.map { it.second.name },
            onValueChange = { value ->
                Preferences.edit {
                    putString(Preferences.autoBackupNetworkTypeKey, value)
                }
                // SMS backup is handled automatically by SmsObserverService
            },
            enabled = isAutoSmsBackupEnabled
        )


        SettingsSectionDivider()

        // Privacy & Security Section - Cloud & Sync
        SettingsSectionHeader(title = "Privacy & Security")

        CloudSmsItem(totalCloudSmsCount = totalCloudSmsCount)

        SettingsListItem(
            title = stringResource(R.string.restore_all_from_cloud),
            subtitle = stringResource(R.string.sms_not_found_on_this_device, totalCloudSmsCount.toString()),
            icon = Icons.Outlined.CloudDownload,
            onClick = {
                // SMS restore functionality not implemented yet
                scope.launch {
                    context.toastFromMainThread(
                        "SMS restore functionality not implemented yet"
                    )
                }
            }
        )

        SettingsSectionDivider()

        // Look & Feel Section - Import/Export
        SettingsSectionHeader(title = "Look & Feel")

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

        SettingsSectionDivider()

        // Memory & Storage Section - Database Management
        SettingsSectionHeader(title = "Memory & Storage")

        DatabaseBackupItem()

        DatabaseStatusItem()

        SettingsSectionDivider()

        // OCR Language Models Section - Placeholder
        SettingsSectionHeader(title = "OCR Language Models")
        SettingsListItem(
            title = "Configure text extraction languages",
            subtitle = "Manage OCR language models and settings",
            icon = Icons.Rounded.Translate,
            onClick = {
                scope.launch {
                    context.toastFromMainThread("OCR settings - Coming soon")
                }
            }
        )

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
    var isSyncing by remember { mutableStateOf(false) }

    val syncSummaryText = if (totalCloudSmsCount > 0) {
        "$totalCloudSmsCount SMS messages synced to cloud"
    } else {
        "No SMS messages synced yet"
    }

    SettingsListItem(
        title = "Cloud SMS Status",
        subtitle = syncSummaryText,
        icon = Icons.Rounded.CloudSync,
        modifier = modifier,
        onClick = {
            scope.launch {
                context.toastFromMainThread("SMS sync is handled automatically by the SMS observer service")
            }
        }
    )
}

@Composable
private fun DatabaseBackupItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var backupStats by remember { mutableStateOf<com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper.BackupStats?>(null) }

    // Load backup stats
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
                // Check connectivity first
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
                            // Refresh backup stats
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
                    data = android.net.Uri.parse("https://github.com/your-repo/sms-sync-app")
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
                    data = android.net.Uri.parse("https://github.com/your-repo/sms-sync-app/blob/main/LICENSE")
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
                    data = android.net.Uri.parse("https://github.com/your-repo/sms-sync-app/graphs/contributors")
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