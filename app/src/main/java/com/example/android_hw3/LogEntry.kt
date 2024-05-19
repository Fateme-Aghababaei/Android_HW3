package com.example.android_hw3

import kotlinx.serialization.Serializable

@Serializable
data class LogEntry(
    val timestamp: String,
    val type: String,
    val status: String
)
