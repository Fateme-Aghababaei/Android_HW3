package com.example.android_hw3

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.android_hw3.ui.theme.Android_HW3Theme
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForPermissions()

        val serviceIntent = Intent(this, InternetService::class.java)
        startService(serviceIntent)

        setContent {
            Android_HW3Theme {
                MyApp()
            }
        }
        setupPeriodicWork()
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


@Composable
fun MyApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = Connect_Status.isInternetConnected, fontWeight = FontWeight.Bold, fontSize = 30.sp)
    }
}