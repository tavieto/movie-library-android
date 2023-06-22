package dev.tavieto.movielibrary.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestLocationPermission(): MultiplePermissionsState {
    val multiplePermissionsState = rememberMultiplePermissionsState(
            listOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    LaunchedEffect(Unit) {
        multiplePermissionsState.launchMultiplePermissionRequest()
        multiplePermissionsState.shouldShowRationale
    }
    return multiplePermissionsState
}
