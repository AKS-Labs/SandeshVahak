package com.akslabs.SandeshVahak.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.akslabs.SandeshVahak.ui.main.nav.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Assuming SyncState is defined elsewhere in this package or imported
// enum class SyncState { <-- REMOVED THIS DEFINITION
//     IDLE,
//     SYNCING,
//     SUCCESS,
//     ERROR
// }

class MainViewModel : ViewModel() {
    var currentDestination by mutableStateOf<Screens>(Screens.LocalSms) // Always start at Device screen
        private set

    fun updateDestination(destination: Screens) {
        currentDestination = destination
    }

    fun resetToDeviceScreen() {
        currentDestination = Screens.LocalSms
    }

    private val _syncState = MutableStateFlow(SyncState.IDLE) // SyncState should resolve from another file now
    val syncState: StateFlow<SyncState> = _syncState.asStateFlow()

    fun updateSyncState(newState: SyncState) {
        _syncState.value = newState
    }
}