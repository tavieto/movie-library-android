package dev.tavieto.movielibrary.core.util

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun notificationPermissionState(): PermissionState {
    return rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
}

@OptIn(ExperimentalPermissionsApi::class)
fun hasShowPermission(permissionState: PermissionState) =
    permissionState.permissionRequested ||
            isTiramisuPlus().not() ||
            permissionState.shouldShowRationale ||
            permissionState.hasPermission

fun isTiramisuPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
