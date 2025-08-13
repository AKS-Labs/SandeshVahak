package com.akslabs.SandeshVahak.ui.main.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.akslabs.SandeshVahak.ui.main.screens.about.AboutScreen
import com.akslabs.chitralaya.ui.main.screens.local.LocalSmsScreen
import com.akslabs.chitralaya.ui.main.screens.local.LocalSmsViewModel
import com.akslabs.chitralaya.ui.main.screens.remote.RemoteSmsScreen
import com.akslabs.chitralaya.ui.main.screens.remote.RemoteSmsViewModel
import com.akslabs.SandeshVahak.ui.main.screens.settings.SettingsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.LocalSms.route, // Always start at Device screen
    onLocalSmsCountChanged: (Int) -> Unit = {},
    onRemoteSmsCountChanged: (Int) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screens.LocalSms.route
        ) {
            val viewModel: LocalSmsViewModel = screenScopedViewModel()
            val localSmsMessages = viewModel.localSmsFlow.collectAsLazyPagingItems()
            val localSmsCount by viewModel.localSmsCount.collectAsStateWithLifecycle(initialValue = 0)

            // Debug paging count flow
            androidx.compose.runtime.SideEffect {
                android.util.Log.d("AppNavHost", "Local count recomposed: $localSmsCount")
            }
            // Notify parent about count changes
            LaunchedEffect(localSmsCount) {
                onLocalSmsCountChanged(localSmsCount)
            }

            LocalSmsScreen(localSmsMessages = localSmsMessages, totalCount = localSmsCount)
        }
        composable(
            route = Screens.RemoteSms.route
        ) {
            val viewModel: RemoteSmsViewModel = screenScopedViewModel()
            val allSyncedSms = viewModel.allSyncedSmsFlow.collectAsLazyPagingItems()
            val totalSyncedSmsCount by viewModel.totalSyncedSmsCount.collectAsStateWithLifecycle(initialValue = 0)

            // Notify parent about count changes
            LaunchedEffect(totalSyncedSmsCount) {
                onRemoteSmsCountChanged(totalSyncedSmsCount)
            }

            RemoteSmsScreen(
                remoteSmsMessages = allSyncedSms,
                totalCount = totalSyncedSmsCount
            )
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen()
        }
        composable(route = Screens.About.route) {
            AboutScreen()
        }
    }
}

/**
 * Provides a [ViewModel] instance scoped the screen's life.
 * When the user navigates away from the screen all screen scoped
 * viewModels are destroyed.
 */
@Composable
inline fun <reified T : ViewModel> screenScopedViewModel(
    factory: ViewModelProvider.Factory? = null,
): T {
    val viewModelStoreOwner = LocalViewModelStoreOwner.current
    requireNotNull(viewModelStoreOwner) { "No ViewModelStoreOwner provided" }
    val viewModelProvider = factory?.let {
        ViewModelProvider(viewModelStoreOwner, it)
    } ?: ViewModelProvider(viewModelStoreOwner)
    return viewModelProvider[T::class.java]
}