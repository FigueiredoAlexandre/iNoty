package com.example.common.presentation.permissions

sealed class PermissionStatus {
    object Granted : PermissionStatus()
    object Denied : PermissionStatus()
    object AlwaysDenied : PermissionStatus()
}