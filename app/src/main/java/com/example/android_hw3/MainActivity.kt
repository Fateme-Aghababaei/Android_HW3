package com.example.android_hw3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android_hw3.ui.theme.Android_HW3Theme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForPermissions()

        val serviceIntent = Intent(this, InternetService::class.java)
        startService(serviceIntent)

        setupPeriodicWork()

        setContent {
            Android_HW3Theme {
                MyUI(context = this)
            }
        }

    }

    private fun setupPeriodicWork() {
        val workRequest =
            PeriodicWorkRequestBuilder<BluetoothAirplaneModeWorker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "BluetoothAirplaneModeWorker",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }
}



