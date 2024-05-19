package com.example.android_hw3

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("ServiceCast")
fun Context.checkForPermissions() {
    val permissionState = ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
    if (permissionState == PackageManager.PERMISSION_DENIED) {
        ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
    }
}