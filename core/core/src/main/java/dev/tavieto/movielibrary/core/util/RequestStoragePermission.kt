package dev.tavieto.movielibrary.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestStoragePermission(): MultiplePermissionsState {
    val multiplePermissionsState = rememberMultiplePermissionsState(
            listOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

    LaunchedEffect(Unit) {
        multiplePermissionsState.launchMultiplePermissionRequest()
        multiplePermissionsState.shouldShowRationale
    }
    return multiplePermissionsState
}
