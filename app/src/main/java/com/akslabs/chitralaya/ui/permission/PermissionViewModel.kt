package com.akslabs.SandeshVahak.ui.permission // Corrected package declaration

import android.Manifest
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PermissionViewModel : ViewModel() {

    val visiblePermissionDialogQueue = mutableStateListOf<String>()
    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (!isGranted &&
            !visiblePermissionDialogQueue.contains(permission) &&
            permission != Manifest.permission.POST_NOTIFICATIONS
        ) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}