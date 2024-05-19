package com.example.android_hw3

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


class BluetoothAirplaneModeWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val isBluetoothEnabled = if (isBluetoothEnabled()) "On" else "Off"
        val isAirplaneModeEnabled = if (isAirplaneModeEnabled()) "On" else "Off"
        logStatus(isBluetoothEnabled, isAirplaneModeEnabled)

        appendLogEntry(context, LogEntry(getCurrentTimestamp(), "Bluetooth", isBluetoothEnabled))
        appendLogEntry(context, LogEntry(getCurrentTimestamp(), "Airplane", isAirplaneModeEnabled))

        return Result.success()
    }

    private fun isBluetoothEnabled(): Boolean {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        return bluetoothAdapter?.isEnabled == true
    }

    private fun isAirplaneModeEnabled(): Boolean {
        return Settings.Global.getInt(
            applicationContext.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
    }

    private fun logStatus(bluetoothEnabled: String, airplaneModeEnabled: String) {
        Log.i(
            "status",
            "Bluetooth: $bluetoothEnabled, Airplane Mode: $airplaneModeEnabled"
        )
    }
}