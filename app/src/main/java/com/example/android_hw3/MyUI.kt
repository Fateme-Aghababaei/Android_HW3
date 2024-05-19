package com.example.android_hw3

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MyUI(context: Context) {
    val logs by remember { mutableStateOf(readLogEntries(context).sortedByDescending {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(it.timestamp)
    }) }

    LazyColumn (
        Modifier.fillMaxWidth().padding(16.dp)
    ) {
        items(logs) { log ->
            LogItem(log)
        }
    }
}

@Composable
fun LogItem(log: LogEntry) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "timestamp: ${log.timestamp}",
            modifier = Modifier
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(text = "type: ${log.type}",
            modifier = Modifier
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(text = "status: ${log.status}",
            modifier = Modifier
                .padding(16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}