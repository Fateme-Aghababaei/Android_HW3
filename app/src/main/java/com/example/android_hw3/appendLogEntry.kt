package com.example.android_hw3

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun appendLogEntry(context: Context, logEntry: LogEntry) {
    val file = File(context.filesDir, "stats.json")
    val json = Json { prettyPrint = true }

    val logEntries: MutableList<LogEntry> = if (file.exists()) {
        val fileContent = file.readText()
        if (fileContent.isNotEmpty()) {
            json.decodeFromString(fileContent)
        } else {
            mutableListOf()
        }
    } else {
        mutableListOf()
    }

    logEntries.add(logEntry)
    file.writeText(json.encodeToString(logEntries))
}

fun readLogEntries(context: Context): List<LogEntry> {
    val file = File(context.filesDir, "stats.json")
    return if (file.exists()) {
        val fileContent = file.readText()
        if (fileContent.isNotEmpty()) {
            Json.decodeFromString(fileContent)
        } else {
            emptyList()
        }
    } else {
        emptyList()
    }
}