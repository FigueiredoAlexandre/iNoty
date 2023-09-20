package com.example.common.presentation.permissions

import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

@RequiresApi(Build.VERSION_CODES.M)
fun FragmentActivity.requestRuntimePermission(
    permission: String,
    callback: (PermissionStatus) -> Unit
): PermissionLauncher {
    return PermissionLauncherLauncherImpl(
        arrayOf(permission),
        registerRequestPermission(permission, callback)
    )
}

@RequiresApi(Build.VERSION_CODES.M)
internal fun FragmentActivity.registerRequestPermission(
    permission: String,
    callback: (PermissionStatus) -> Unit
): ActivityResultLauncher<Array<String>> {
   return requestPermissions(arrayOf(permission)) {
       it[permission]?.let { permissionStatus ->
           callback(permissionStatus)
       }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
internal fun FragmentActivity.requestPermissions(
    permissions: Array<String>,
    callback: (Map<String, PermissionStatus>) -> Unit
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        processPermissions(
            it,
            permissions
        ) { permission ->
            shouldShowRequestPermissionRationale(permission)
        }.also { permissionsMap ->
            callback(permissionsMap)
        }
    }
}

internal  fun processPermissions(
    requestPermissionResult: Map<String, Boolean>,
    permissions: Array<String>,
    shouldShowRequestPermissionRationale: (permissions: String) -> Boolean
): Map<String, PermissionStatus> {
    return permissions
        .map { key ->
            getPermissionResult(
                key = key,
                permissionIsGranted = requestPermissionResult[key] ?: true,
                shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(key)
            )
        }
        .reduce { acc, map -> map + acc }
        .takeIf { permissions.size >= requestPermissionResult.size }
        ?: throw IllegalStateException("Error while processing permission list")
}

internal fun getPermissionResult(
    key: String,
    permissionIsGranted: Boolean,
    shouldShowRequestPermissionRationale: Boolean
): Map<String, PermissionStatus> {
    return mapOf(
        key to getPermissionStatus(
            permissionIsGranted, shouldShowRequestPermissionRationale
        )
    )
}

internal fun getPermissionStatus(
    permissionIsGranted: Boolean,
    shouldShowRequestPermissionRationale: Boolean
): PermissionStatus {
    return when {
        permissionIsGranted -> PermissionStatus.Granted
        permissionIsGranted.not() && shouldShowRequestPermissionRationale.not() -> {
            PermissionStatus.AlwaysDenied
        }
        else -> PermissionStatus.Denied
    }
}