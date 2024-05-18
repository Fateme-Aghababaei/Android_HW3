package com.example.android_hw3

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun writeToFile(context: Context, logObject: JSONObject = JSONObject()) {
    val logArray = JSONArray().put(logObject)
    val logFile = File(context.filesDir, "logs.json")

    logFile.appendText(logArray.toString() )
    //Log.d("fat", "Log entries saved to: ${logFile.absolutePath}")

//    val reader = BufferedReader(FileReader(logFile))
//    var line: String? = reader.readLine()
//    while (line != null) {
//        Log.d("fat", line)
//        line = reader.readLine()
//    }
//    reader.close()
}

fun getCurrentTimestamp(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}