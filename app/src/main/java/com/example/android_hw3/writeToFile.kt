package com.example.android_hw3

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTimestamp(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}