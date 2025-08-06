package com.akslabs.Suchak.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.akslabs.Suchak.R
import com.akslabs.Suchak.data.localdb.Preferences
import com.akslabs.Suchak.ui.components.ConnectivityStatusPopup
import com.akslabs.Suchak.ui.main.nav.AppNavHost
import com.akslabs.Suchak.ui.main.nav.Screens
import com.akslabs.Suchak.ui.main.nav.screenScopedViewModel
import com.akslabs.Suchak.workers.WorkModule
import com.akslabs.Suchak.workers.WorkModule.SYNC_MEDIA_STORE_WORK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(viewModel: MainViewModel = screenScopedViewModel()) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val syncState by viewModel.syncState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Enable edge-to-edge display
    LaunchedEffect(Unit) {
        val activity = context as? androidx.activity.ComponentActivity
        activity?.let {
            WindowCompat.setDecorFitsSystemWindows(it.window, false)
        }
    }

    // Track current destination for bottom navigation
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    // Scrollable TopAppBar behavior - use enterAlways for immediate reappearance
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    // Track message counts for TopAppBar title
    var localSmsCount by remember { mutableIntStateOf(0) }
    var remoteSmsCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(viewModel) {
        val initialPage = if (viewModel.currentDestination in Screens.mainScreens) {
            val lastSelectedTab = Preferences.getString(Preferences.startTabKey, "")
            Screens.mainScreens.firstOrNull { it.route == lastSelectedTab }
        } else {
            viewModel.currentDestination
        }
        initialPage?.route?.runCatching { navController.navigate(this) }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (Screens.mainScreens.any { it.route == destination.route }) {
                Preferences.edit { putString(Preferences.startTabKey, destination.route) }
            }
        }
    }

    LaunchedEffect(viewModel) {
        WorkModule.SyncMediaStore.enqueueInstant()
        scope.launch {
            WorkModule.observeWorkerByName(SYNC_MEDIA_STORE_WORK)
                .collectLatest {
                    it.firstOrNull()?.let { workInfo ->
                        when (workInfo.state) {
                            WorkInfo.State.RUNNING -> viewModel.updateSyncState(SyncState.SYNCING)
                            WorkInfo.State.SUCCEEDED -> {
                                viewModel.updateSyncState(SyncState.IDLE)
                                WorkModule.SyncMediaStore.enqueuePeriodic()
                            }
                            else -> viewModel.updateSyncState(SyncState.IDLE)
                        }
                    }
                }
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
                            // Get count for title display with dynamic counts
                            val titleWithCount = when (currentDestination) {
                                Screens.LocalSms.route -> {
                                    if (localSmsCount > 0) "Device ($localSmsCount)" else "Device"
                                }
                                Screens.RemoteSms.route -> {
                                    if (remoteSmsCount > 0) "Cloud ($remoteSmsCount)" else "Cloud"
                                }
                                Screens.Settings.route -> Screens.Settings.displayTitle
                                else -> "SMS Sync"
                            }
                            Text(
                                text = titleWithCount,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    navController.navigate(Screens.Settings.route)
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
            contentWindowInsets = WindowInsets(0, 0, 0, 0) // Allow content to extend into system bars
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                AppNavHost(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    navController = navController,
                    onLocalSmsCountChanged = { count -> localSmsCount = count },
                    onRemoteSmsCountChanged = { count -> remoteSmsCount = count }
                )

                // Truly floating bottom navigation
                if (currentDestination in listOf(Screens.LocalSms.route, Screens.RemoteSms.route)) {
                    TrulyFloatingBottomNavigation(
                        navController = navController,
                        currentDestination = currentDestination,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }

        // Only show syncing animation on cloud photos screen, not device screen
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
                        text = stringResource(R.string.syncing_your_photos),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
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
            .wrapContentWidth(), // Only take up needed width
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.9f), // Semi-transparent
        tonalElevation = 12.dp,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Device Tab
            FloatingNavItem(
                icon = Icons.Default.Smartphone,
                label = "Device",
                isSelected = currentDestination == Screens.LocalSms.route,
                onClick = {
                    if (currentDestination != Screens.LocalSms.route) {
                        navController.navigate(Screens.LocalSms.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

            // Cloud Tab
            FloatingNavItem(
                icon = Icons.Default.Cloud,
                label = "Cloud",
                isSelected = currentDestination == Screens.RemoteSms.route,
                onClick = {
                    if (currentDestination != Screens.RemoteSms.route) {
                        navController.navigate(Screens.RemoteSms.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
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
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            Color.Transparent
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) {
                    MaterialTheme.colorScheme.onSecondaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
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
