package com.akslabs.Suchak.ui.main.screens.settings

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
import com.akslabs.Suchak.R
import com.akslabs.Suchak.data.localdb.DbHolder
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.Suchak.data.localdb.backup.BackupHelper
import com.akslabs.Suchak.services.CloudPhotoSyncService
import com.akslabs.Suchak.ui.components.SettingsListItem
import com.akslabs.Suchak.ui.components.SettingsListItemWithSwitch
import com.akslabs.Suchak.ui.components.SettingsListItemWithDialog
import com.akslabs.Suchak.ui.components.SettingsSectionHeader
import com.akslabs.Suchak.ui.components.SettingsSectionDivider
import com.akslabs.Suchak.utils.toastFromMainThread
import com.akslabs.Suchak.workers.WorkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // State for settings
    var isAutoPhotoBackupEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoBackupEnabledKey, false)
        )
    }

    var isAutoExportDatabaseEnabled by remember {
        mutableStateOf(
            Preferences.getBoolean(Preferences.isAutoExportDatabaseEnabledKey, false)
        )
    }

    val totalCloudPhotosCount by DbHolder.database.remotePhotoDao().getTotalCountFlow()
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
            subtitle = "Automatically backup photos to cloud",
            icon = Icons.Rounded.CloudSync,
            isChecked = isAutoPhotoBackupEnabled,
            onCheckedChange = { checked ->
                isAutoPhotoBackupEnabled = checked
                Preferences.edit {
                    putBoolean(Preferences.isAutoBackupEnabledKey, checked)
                }
                if (checked) {
                    WorkModule.PeriodicBackup.enqueue()
                    scope.launch {
                        context.toastFromMainThread(context.getString(R.string.periodic_backup_enabled))
                    }
                } else {
                    WorkModule.PeriodicBackup.cancel()
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
                WorkModule.PeriodicBackup.enqueue(forceUpdate = true)
            },
            enabled = isAutoPhotoBackupEnabled
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
                WorkModule.PeriodicBackup.enqueue(forceUpdate = true)
            },
            enabled = isAutoPhotoBackupEnabled
        )

        SettingsSectionDivider()

        // Privacy & Security Section - Cloud & Sync
        SettingsSectionHeader(title = "Privacy & Security")

        CloudPhotoSyncItem(totalCloudPhotosCount = totalCloudPhotosCount)

        SettingsListItem(
            title = stringResource(R.string.restore_all_from_cloud),
            subtitle = stringResource(R.string.photos_not_found_on_this_device, totalCloudPhotosCount),
            icon = Icons.Outlined.CloudDownload,
            onClick = {
                WorkModule.RestoreMissingFromDevice.enqueue()
                scope.launch {
                    context.toastFromMainThread(
                        context.getString(R.string.restoring_task_enqueued_in_the_background)
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
                    context.getString(R.string.Suchak_photos_backup_json)
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
                        context.getString(R.string.Suchak_auto_photos_backup_json)
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
private fun CloudPhotoSyncItem(
    totalCloudPhotosCount: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isSyncing by remember { mutableStateOf(false) }
    var syncProgress by remember { mutableStateOf<com.akslabs.Suchak.api.ScanProgress?>(null) }
    var syncStats by remember { mutableStateOf<com.akslabs.Suchak.services.SyncStatistics?>(null) }

    // Load sync statistics
    LaunchedEffect(Unit) {
        syncStats = CloudPhotoSyncService.getSyncStatistics()
    }

    val syncSummaryText = when {
        isSyncing && syncProgress != null -> {
            val progress = syncProgress!!
            if (progress.isComplete) {
                if (progress.errorMessage != null) {
                    if (progress.errorMessage.contains("No Telegram channel configured")) {
                        "Setup required: Send /start to your Telegram bot first"
                    } else {
                        "Sync failed: ${progress.errorMessage}"
                    }
                } else {
                    "Sync complete! Found ${progress.totalFilesFound} new photos"
                }
            } else {
                "Syncing... Batch ${progress.currentBatch}, Found ${progress.totalFilesFound} photos"
            }
        }
        syncStats != null -> {
            val stats = syncStats!!
            val hoursAgo = stats.timeSinceLastSyncMs / (1000 * 60 * 60)
            when {
                stats.lastSyncTimestamp == 0L -> "Never synced. Note: Bot API only finds photos from last 24 hours"
                stats.isSyncOverdue -> "Last synced $hoursAgo hours ago. Sync recommended"
                else -> "Last synced $hoursAgo hours ago. $totalCloudPhotosCount photos in cloud"
            }
        }
        else -> "Loading sync status..."
    }

    SettingsListItem(
        title = "Sync Cloud Photos",
        subtitle = syncSummaryText,
        icon = Icons.Rounded.CloudSync,
        modifier = modifier,
        onClick = {
            if (!isSyncing) {
                scope.launch {
                    isSyncing = true
                    try {
                        CloudPhotoSyncService.forceSync(context).collect { progress ->
                            syncProgress = progress
                            if (progress.isComplete) {
                                isSyncing = false
                                // Refresh sync stats
                                syncStats = CloudPhotoSyncService.getSyncStatistics()

                                val message = if (progress.errorMessage != null) {
                                    "Sync failed: ${progress.errorMessage}"
                                } else {
                                    "Sync complete! Found ${progress.totalFilesFound} new photos"
                                }
                                context.toastFromMainThread(message)
                            }
                        }
                    } catch (e: Exception) {
                        isSyncing = false
                        context.toastFromMainThread("Sync error: ${e.message}")
                    }
                }
            }
        }
    )
}

@Composable
private fun DatabaseBackupItem(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var backupStats by remember { mutableStateOf<com.akslabs.Suchak.data.localdb.backup.BackupHelper.BackupStats?>(null) }

    // Load backup stats
    LaunchedEffect(Unit) {
        backupStats = com.akslabs.Suchak.data.localdb.backup.BackupHelper.getBackupStats()
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
                try {
                    context.toastFromMainThread("Uploading database to Telegram...")
                    val result = com.akslabs.Suchak.data.localdb.backup.BackupHelper.uploadDatabaseToTelegram(context)
                    result.fold(
                        onSuccess = { message ->
                            context.toastFromMainThread("✅ $message")
                            // Refresh backup stats
                            backupStats = com.akslabs.Suchak.data.localdb.backup.BackupHelper.getBackupStats()
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
                val stats = com.akslabs.Suchak.data.localdb.backup.BackupHelper.getBackupStats()
                val message = buildString {
                    appendLine("📊 Database Status:")
                    appendLine("• Photos: ${stats.currentPhotos}")
                    appendLine("• Remote Photos: ${stats.currentRemotePhotos}")
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