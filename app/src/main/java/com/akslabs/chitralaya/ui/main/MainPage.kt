package com.akslabs.SandeshVahak.ui.main

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.activity.compose.BackHandler

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkInfo
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService
import com.akslabs.SandeshVahak.services.SmsContentObserver
import com.akslabs.SandeshVahak.ui.components.ConnectivityStatusPopup // Corrected import
import com.akslabs.SandeshVahak.ui.main.nav.AppNavHost // Corrected import
import com.akslabs.SandeshVahak.ui.main.nav.Screens // Corrected import
import com.akslabs.SandeshVahak.ui.main.nav.screenScopedViewModel // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule // Corrected import
import com.akslabs.SandeshVahak.ui.main.SyncState // Assuming SyncState will be in this package or imported correctly later

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(viewModel: MainViewModel = screenScopedViewModel()) {
    val navController = rememberNavController()
    val syncState by viewModel.syncState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val activity = context as? androidx.activity.ComponentActivity
        activity?.let {
            WindowCompat.setDecorFitsSystemWindows(it.window, false)
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var localSmsCount by remember { mutableIntStateOf(0) }
    var remoteSmsCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(viewModel) {
        navController.navigate(Screens.LocalSms.route)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (Screens.mainScreens.any { it.route == destination.route }) {
                Preferences.edit { putString(Preferences.startTabKey, destination.route) }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.resetToDeviceScreen()
        navController.navigate(Screens.LocalSms.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.updateSyncState(SyncState.IDLE)
    }

    var isSyncEnabledForTopAppBar by remember { mutableStateOf(Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)) }
    var showModeDialog by remember { mutableStateOf(false) }
    val greenAccentColor = Color(0xFF2E7D32)

    LaunchedEffect(currentDestination, Unit) { // Re-check on navigation or initial load
        isSyncEnabledForTopAppBar = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize(),
            topBar = {
                Column {
                    TopAppBar(
                        title = {
                            val titleWithCount = when (currentDestination) {
                                Screens.LocalSms.route -> if (localSmsCount > 0) "Device ($localSmsCount)" else "Device"
                                Screens.RemoteSms.route -> if (remoteSmsCount > 0) "Cloud ($remoteSmsCount)" else "Cloud"
                                Screens.Settings.route -> Screens.Settings.displayTitle
                                else -> "SMS Sync"
                            }
                            Text(text = titleWithCount, color = MaterialTheme.colorScheme.onSurface)
                        },
                        actions = {
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        if (isSyncEnabledForTopAppBar) {
                                            Preferences.edit { putBoolean(Preferences.isSmsSyncEnabledKey, false) }
                                            isSyncEnabledForTopAppBar = false
                                            SmsObserverService.stop(context) // STOP SERVICE
                                            SmsContentObserver.stopObserving(context) // STOP STATIC OBSERVER
                                        } else {
                                            showModeDialog = true
                                        }
                                    }
                                    .padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                val iconTint = if (isSyncEnabledForTopAppBar) greenAccentColor else MaterialTheme.colorScheme.onSurfaceVariant
                                val textColor = if (isSyncEnabledForTopAppBar) greenAccentColor else MaterialTheme.colorScheme.onSurface
                                Icon(
                                    imageVector = if (isSyncEnabledForTopAppBar) Icons.Default.CloudUpload else Icons.Default.CloudOff,
                                    contentDescription = "SMS Sync Toggle",
                                    tint = iconTint
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(text = if (isSyncEnabledForTopAppBar) "Sync: ON" else "Sync: OFF", color = textColor)
                            }

                            IconButton(
                                onClick = { navController.navigate(Screens.Settings.route) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Settings",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            scrolledContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        windowInsets = WindowInsets.statusBars
                    )
                    ConnectivityStatusPopup()
                }
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                AppNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    onLocalSmsCountChanged = { count -> localSmsCount = count },
                    onRemoteSmsCountChanged = { count -> remoteSmsCount = count }
                )

                if (currentDestination in listOf(Screens.LocalSms.route, Screens.RemoteSms.route)) {
                    TrulyFloatingBottomNavigation(
                        navController = navController,
                        currentDestination = currentDestination,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }

        if (showModeDialog) {
            val currentSyncModeFromPrefs = Preferences.getString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
            val isCurrentlyEnabledInPrefs = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
            val initialDialogSelection = if (isCurrentlyEnabledInPrefs) currentSyncModeFromPrefs else "OFF"

            AlertDialog(
                onDismissRequest = { showModeDialog = false },
                title = { Text("SMS Cloud Sync Options") },
                text = {
                    Column(Modifier.selectableGroup()) {
                        DialogRadioOption(
                            text = "Sync all existing and new messages",
                            selected = initialDialogSelection == Preferences.SMS_SYNC_MODE_ALL,
                            onClick = {
                                Preferences.edit {
                                    putBoolean(Preferences.isSmsSyncEnabledKey, true)
                                    putString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_ALL)
                                    putLong(Preferences.smsSyncEnabledSinceKey, 0L) // Reset baseline for full sync
                                }
                                isSyncEnabledForTopAppBar = true
                                SmsObserverService.start(context) // START SERVICE
                                SmsContentObserver.startObserving(context) // START STATIC OBSERVER
                                showModeDialog = false
                            }
                        )
                        DialogRadioOption(
                            text = "Sync only new messages",
                            selected = initialDialogSelection == Preferences.SMS_SYNC_MODE_NEW_ONLY,
                            onClick = {
                                val currentSinceKeyValue = Preferences.getLong(Preferences.smsSyncEnabledSinceKey, 0L)
                                Preferences.edit {
                                    putBoolean(Preferences.isSmsSyncEnabledKey, true)
                                    putString(Preferences.smsSyncModeKey, Preferences.SMS_SYNC_MODE_NEW_ONLY)
                                    if (currentSinceKeyValue == 0L) { // Only set new timestamp if switching from ALL or freshly enabling
                                        putLong(Preferences.smsSyncEnabledSinceKey, System.currentTimeMillis())
                                    }
                                }
                                isSyncEnabledForTopAppBar = true
                                SmsObserverService.start(context) // START SERVICE
                                SmsContentObserver.startObserving(context) // START STATIC OBSERVER
                                showModeDialog = false
                            }
                        )
                        DialogRadioOption(
                            text = "Turn sync off",
                            selected = initialDialogSelection == "OFF",
                            onClick = {
                                Preferences.edit { putBoolean(Preferences.isSmsSyncEnabledKey, false) }
                                isSyncEnabledForTopAppBar = false
                                SmsObserverService.stop(context) // STOP SERVICE
                                SmsContentObserver.stopObserving(context) // STOP STATIC OBSERVER
                                showModeDialog = false
                            }
                        )
                    }
                },
                confirmButton = { }, 
                dismissButton = null
            )
        }

        AnimatedVisibility(visible = syncState == SyncState.SYNCING && currentDestination == Screens.RemoteSms.route) {
            Dialog({}) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        strokeCap = StrokeCap.Square
                    )
                    Spacer(Modifier.size(16.dp))
                    Text(
                        text = stringResource(R.string.syncing_your_sms),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogRadioOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick, 
                role = Role.RadioButton
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null 
        )
        Spacer(Modifier.width(16.dp))
        Text(text)
    }
}


@Composable
private fun TrulyFloatingBottomNavigation(
    navController: NavController,
    currentDestination: String?,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .wrapContentWidth(),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.9f),
        tonalElevation = 12.dp,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FloatingNavItem(
                icon = Icons.Default.Smartphone,
                label = "Device",
                isSelected = currentDestination == Screens.LocalSms.route,
                onClick = {
                    if (currentDestination != Screens.LocalSms.route) {
                        navController.navigate(Screens.LocalSms.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            FloatingNavItem(
                icon = Icons.Default.Cloud,
                label = "Cloud",
                isSelected = currentDestination == Screens.RemoteSms.route,
                onClick = {
                    if (currentDestination != Screens.RemoteSms.route) {
                        navController.navigate(Screens.RemoteSms.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun FloatingNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
            if (isSelected) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
