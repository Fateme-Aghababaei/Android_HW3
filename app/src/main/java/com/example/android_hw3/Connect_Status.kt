package com.example.android_hw3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Connect_Status {
    var isBluetoothOn: String by mutableStateOf("")
    var isAirplaneOn: String by mutableStateOf("")

}
