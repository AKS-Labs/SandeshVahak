package com.akslabs.SandeshVahak.ui.main

import android.content.Intent
import android.util.Log // Added import
import androidx.compose.animation.AnimatedVisibility
import androidx.activity.compose.BackHandler // Keep if used, though not visible in snippet for this change

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.* // Existing M3 import, should cover ripple
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
import androidx.compose.ui.text.font.FontWeight // Added for fontWeight access
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination // For popUpTo
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkInfo
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.services.SmsObserverService
import com.akslabs.SandeshVahak.services.SmsContentObserver
import com.akslabs.SandeshVahak.ui.components.ConnectivityStatusPopup
import com.akslabs.SandeshVahak.ui.main.nav.AppNavHost
import com.akslabs.SandeshVahak.ui.main.nav.Screens
import com.akslabs.SandeshVahak.ui.main.nav.screenScopedViewModel
import com.akslabs.SandeshVahak.workers.WorkModule
import com.akslabs.SandeshVahak.ui.main.SyncState

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull // To wait for non-null navBackStackEntry
import kotlinx.coroutines.flow.first // To take the first non-null value
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(viewModel: MainViewModel = screenScopedViewModel()) {
    val navController = rememberNavController()
    val syncState by viewModel.syncState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Effect for WindowCompat - KEEP
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

    // Refactored Effect for initial setup using snapshotFlow
    LaunchedEffect(Unit) { // Runs once when MainPage enters composition
        // Wait for NavHost to be ready by observing currentBackStackEntry
        snapshotFlow { navController.currentBackStackEntry }
            .filterNotNull() // Wait until it's not null
            .first() // Take the first non-null value, ensuring this block runs once

        // Now that NavHost is ready and we have an entry, proceed with setup
        viewModel.resetToDeviceScreen()

        if (navController.currentDestination?.route != Screens.LocalSms.route || navController.previousBackStackEntry != null) {
            navController.navigate(Screens.LocalSms.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    // DisposableEffect to manage the NavController listener - KEEP
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (Screens.mainScreens.any { it.route == destination.route }) {
                Preferences.edit { putString(Preferences.startTabKey, destination.route) }
            }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
    
    // Original LaunchedEffect for viewModel.updateSyncState - KEEP
    LaunchedEffect(viewModel) {
        viewModel.updateSyncState(SyncState.IDLE)
    }

    var isSyncEnabledForTopAppBar by remember { mutableStateOf(Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)) }
    var showModeDialog by remember { mutableStateOf(false) } 
    val greenAccentColor = Color(0xFF2E7D32)

    // Original LaunchedEffect for isSyncEnabledForTopAppBar - KEEP
    LaunchedEffect(currentDestination, Unit) { 
        isSyncEnabledForTopAppBar = Preferences.getBoolean(Preferences.isSmsSyncEnabledKey, false)
    }

    // New LaunchedEffect to ensure service is started in foreground if sync is enabled when UI is active
    LaunchedEffect(isSyncEnabledForTopAppBar, context) {
        if (isSyncEnabledForTopAppBar) {
            Log.d("MainPage", "Sync is enabled, ensuring SmsObserverService is started with ACTION_START_SERVICE.")
            SmsObserverService.start(context, SmsObserverService.ACTION_START_SERVICE)
        }
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
                                else -> "SMS Sync" // Default title
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
                                            SmsObserverService.stop(context)
                                            SmsContentObserver.stopObserving(context)
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
                                onClick = { 
                                    if (currentDestination != Screens.Settings.route) {
                                        navController.navigate(Screens.Settings.route)
                                    }
                                }
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
            contentWindowInsets = WindowInsets(0,0,0,0) 
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
            val initialDialogSelection = when {
                isCurrentlyEnabledInPrefs && currentSyncModeFromPrefs == Preferences.SMS_SYNC_MODE_ALL -> Preferences.SMS_SYNC_MODE_ALL
                isCurrentlyEnabledInPrefs && currentSyncModeFromPrefs == Preferences.SMS_SYNC_MODE_NEW_ONLY -> Preferences.SMS_SYNC_MODE_NEW_ONLY
                else -> "OFF" 
            }

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
                                    putLong(Preferences.smsSyncEnabledSinceKey, 0L) 
                                }
                                isSyncEnabledForTopAppBar = true
                                // ACTION_START_SERVICE is the default, ensuring foreground promotion
                                SmsObserverService.start(context) 
                                SmsContentObserver.startObserving(context)
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
                                    if (currentSinceKeyValue == 0L || initialDialogSelection == Preferences.SMS_SYNC_MODE_ALL) { 
                                        putLong(Preferences.smsSyncEnabledSinceKey, System.currentTimeMillis())
                                    }
                                }
                                isSyncEnabledForTopAppBar = true
                                // ACTION_START_SERVICE is the default, ensuring foreground promotion
                                SmsObserverService.start(context)
                                SmsContentObserver.startObserving(context)
                                showModeDialog = false
                            }
                        )
                        DialogRadioOption(
                            text = "Turn sync off",
                            selected = initialDialogSelection == "OFF",
                            onClick = {
                                Preferences.edit { putBoolean(Preferences.isSmsSyncEnabledKey, false) }
                                isSyncEnabledForTopAppBar = false
                                SmsObserverService.stop(context) 
                                SmsContentObserver.stopObserving(context) 
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
            Dialog(onDismissRequest = { /* Sync in progress, usually not dismissible */ }) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary, 
                        strokeWidth = 4.dp 
                    )
                    Spacer(Modifier.size(16.dp))
                    Text(
                        text = stringResource(R.string.syncing_your_sms),
                        color = MaterialTheme.colorScheme.onSurface, 
                        style = MaterialTheme.typography.bodyMedium 
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
            onClick = null, 
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary) 
        )
        Spacer(Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge) 
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
            .padding(horizontal = 32.dp, vertical = 24.dp) 
            .wrapContentSize(), 
        shape = RoundedCornerShape(28.dp), 
        color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.95f), 
        tonalElevation = 8.dp, 
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp) 
                .selectableGroup(), 
            horizontalArrangement = Arrangement.spacedBy(12.dp) 
        ) {
            FloatingNavItem(
                icon = Icons.Default.Smartphone,
                label = "Device",
                isSelected = currentDestination == Screens.LocalSms.route,
                onClick = {
                    if (currentDestination != Screens.LocalSms.route) {
                        navController.navigate(Screens.LocalSms.route) {
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
    val interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    Surface(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = ripple(bounded = false, radius = 40.dp), // Corrected: Using M3 ripple
            onClick = onClick,
            role = Role.Tab 
        ),
        shape = RoundedCornerShape(20.dp), 
        color = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent,
        contentColor = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp), 
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) 
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null, 
                modifier = Modifier.size(20.dp) 
            )
            AnimatedVisibility(visible = isSelected) { 
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge, 
                    fontWeight = FontWeight.Medium // Corrected fontWeight access
                )
            }
        }
    }
}
