package com.akslabs.SandeshVahak.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akslabs.SandeshVahak.data.localdb.entities.SmsMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SmsListItem(
    smsMessage: SmsMessage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), // Remove shadow for cleaner look
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Message type icon with device overlay
            Box(
                modifier = Modifier.size(40.dp)
            ) {
                // Main icon
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            if (smsMessage.isReceived())
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.secondaryContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (smsMessage.isReceived()) Icons.Default.Sms else Icons.Default.Send,
                        contentDescription = smsMessage.getTypeDescription(),
                        tint = if (smsMessage.isReceived())
                            MaterialTheme.colorScheme.onPrimaryContainer
                        else
                            MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Device indicator overlay
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .align(Alignment.BottomEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Device SMS",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            // Message content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Header row with contact and timestamp
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = smsMessage.getDisplayAddress(),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = formatTimestamp(smsMessage.date),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Message body
                Text(
                    text = smsMessage.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Status row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Sync status icon and text
                    val (statusIcon, statusColor, statusText) = when {
                        smsMessage.isSynced -> Triple(
                            Icons.Default.CheckCircle,
                            Color(0xFF4CAF50),
                            "Synced to Telegram"
                        )
                        smsMessage.syncAttempts > 0 && smsMessage.syncError != null -> Triple(
                            Icons.Default.Error,
                            Color(0xFFF44336),
                            "Sync failed"
                        )
                        smsMessage.syncAttempts > 0 -> Triple(
                            Icons.Default.Schedule,
                            Color(0xFFFF9800),
                            "Syncing to Telegram"
                        )
                        else -> Triple(
                            Icons.Default.Schedule,
                            MaterialTheme.colorScheme.onSurfaceVariant,
                            "Pending sync"
                        )
                    }

                    Icon(
                        imageVector = statusIcon,
                        contentDescription = statusText,
                        tint = statusColor,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.bodySmall,
                        color = statusColor,
                        fontSize = 11.sp
                    )

                    // Show additional info and retry for failed syncs
                    if (smsMessage.syncError != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "• ${getErrorSummary(smsMessage.syncError)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp
                        )

                        // Add retry button for failed syncs
                        if (smsMessage.syncAttempts > 0 && !smsMessage.isSynced) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF2196F3).copy(alpha = 0.1f))
                                    .clickable {
                                        // TODO: Implement retry functionality
                                        // This would trigger a retry for this specific message
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Retry sync",
                                    tint = Color(0xFF2196F3),
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    return when {
        diff < 60 * 1000 -> "Just now"
        diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)}m ago"
        diff < 24 * 60 * 60 * 1000 -> {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))
        }
        diff < 7 * 24 * 60 * 60 * 1000 -> {
            SimpleDateFormat("EEE HH:mm", Locale.getDefault()).format(Date(timestamp))
        }
        else -> {
            SimpleDateFormat("MMM dd", Locale.getDefault()).format(Date(timestamp))
        }
    }
}

/**
 * Get a user-friendly summary of the sync error
 */
private fun getErrorSummary(error: String?): String {
    if (error == null) return "Failed"

    return when {
        error.contains("rate limit", ignoreCase = true) -> "Rate limited"
        error.contains("network", ignoreCase = true) -> "Network error"
        error.contains("unauthorized", ignoreCase = true) -> "Auth error"
        error.contains("forbidden", ignoreCase = true) -> "Permission error"
        error.contains("not found", ignoreCase = true) -> "Channel not found"
        error.contains("bad request", ignoreCase = true) -> "Invalid format"
        else -> "Sync failed"
    }
}
