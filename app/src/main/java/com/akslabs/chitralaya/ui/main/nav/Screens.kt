package com.akslabs.Suchak.ui.main.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val displayTitle: String, val route: String, val icon: ImageVector? = null) {
    data object RemoteSms :
        Screens(displayTitle = "Synced SMS", route = "synced_sms", icon = Icons.Default.Cloud)

    data object LocalSms :
        Screens(displayTitle = "Local SMS", route = "local_sms", icon = Icons.Default.Smartphone)

    data object Settings :
        Screens(displayTitle = "Settings", route = "settings", icon = Icons.Default.Settings)

    data object About : Screens(displayTitle = "About", route = "about", icon = Icons.Default.Info)

    companion object {
        val mainScreens by lazy { listOf(LocalSms, RemoteSms) }
        val allScreens by lazy { listOf(LocalSms, RemoteSms, Settings, About) }
        // Keep drawerScreens for backward compatibility
        val drawerScreens by lazy { allScreens }
    }
}