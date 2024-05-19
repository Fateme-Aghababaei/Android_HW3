package com.example.android_hw3

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.android_hw3.ui.theme.Android_HW3Theme
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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



