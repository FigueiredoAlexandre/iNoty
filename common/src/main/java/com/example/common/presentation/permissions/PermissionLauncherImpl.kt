package com.example.common.presentation.permissions

import androidx.activity.result.ActivityResultLauncher

class PermissionLauncherLauncherImpl(
    private val permissions: Array<String>,
    private val result: ActivityResultLauncher<Array<String>>
) : PermissionLauncher {

    override fun requestPermission() {
        result.launch(permissions)
    }
}