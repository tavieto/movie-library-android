package dev.tavieto.movielibrary.core.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings

fun Context.getPackageInfo(): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.PackageInfoFlags.of(PackageManager.GET_ACTIVITIES.toLong())
        )
    } else {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.GET_ACTIVITIES
        )
    }
}

fun Context.getVersionName(): String = getPackageInfo().versionName

fun Context.openSettings() {
    val settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    settingsIntent.data = Uri.fromParts("package", packageName, null)
    startActivity(settingsIntent)
}
