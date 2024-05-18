package com.example.android_hw3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android_hw3.ui.theme.Android_HW3Theme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_HW3Theme {

            }
        }
        setupPeriodicWork()
    }

    private fun setupPeriodicWork() {
        val workRequest =
            PeriodicWorkRequestBuilder<BluetoothAirplaneModeWorker>(15, TimeUnit.MINUTES).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
