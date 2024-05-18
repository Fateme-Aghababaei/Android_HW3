package com.example.android_hw3

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.os.IBinder
import android.util.Log
import org.json.JSONObject

class InternetService: Service() {
    private var receiver: BroadcastReceiver? = null

    override fun onCreate() {
        super.onCreate()

        val filter = IntentFilter(CONNECTIVITY_ACTION)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = cm.activeNetworkInfo
                if (networkInfo != null && networkInfo.isConnected) {
                    sendNotification(context, "Status", "Internet Connected.")
                    Log.v("status", "Internet Connection: true")
                    Connect_Status.isInternetConnected = "Internet Connected."

                    val currentDate = getCurrentTimestamp()
                    val logObject = JSONObject()
                    logObject.put("timestamp", currentDate)
                    logObject.put("type", "Internet")
                    logObject.put("status", Connect_Status.isInternetConnected)
                    writeToFile(context, logObject)
                } else {
                    sendNotification(context, "Status", "Internet Disconnected.")
                    Log.v("status", "Internet Connection: false")
                    Connect_Status.isInternetConnected = "Internet Disconnected."

                    val currentDate = getCurrentTimestamp()
                    val logObject = JSONObject()
                    logObject.put("timestamp", currentDate)
                    logObject.put("type", "Internet")
                    logObject.put("status", Connect_Status.isInternetConnected)
                    writeToFile(context, logObject)
                }
            }
        }
        registerReceiver(receiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}