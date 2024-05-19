package com.example.android_hw3

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

@SuppressLint("ServiceCast", "RestrictedApi")
fun sendNotification(context: Context, title: String, message: String) {
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "default_channel_id"
    val channelName = "Default Channel"

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_notification_icon)
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.internet)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    notificationManager.notify(1, notificationBuilder.build())
}