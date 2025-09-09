package com.akslabs.chitralaya.ui.main.screens.remote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import com.akslabs.chitralaya.ui.components.LoadAnimation
import com.akslabs.chitralaya.ui.components.RemoteSmsListItem
import com.akslabs.chitralaya.ui.components.itemsPaging

@Composable
fun RemoteSmsScreen(
    remoteSmsMessages: LazyPagingItems<RemoteSmsMessage>,
    totalCount: Int,
    modifier: Modifier = Modifier
) {
    var selectedSmsMessage by remember { mutableStateOf<RemoteSmsMessage?>(null) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            remoteSmsMessages.loadState.refresh == LoadState.Loading -> {
                LoadAnimation(modifier = Modifier.align(Alignment.Center))
            }

            remoteSmsMessages.itemCount == 0 && remoteSmsMessages.loadState.refresh is LoadState.NotLoading -> {
                // Empty state
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No synced SMS messages",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "SMS messages that have been successfully synced to Telegram will appear here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 88.dp), // Add bottom padding for floating nav
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    itemsPaging(
                        items = remoteSmsMessages,
                        key = { it.remoteId }
                    ) { remoteSmsMessage, _ ->
                        remoteSmsMessage?.let {
                            RemoteSmsListItem(
                                remoteSmsMessage = it,
                                onClick = { selectedSmsMessage = it }
                            )
                        }
                    }
                }
            }
        }

        // SMS detail dialog
        selectedSmsMessage?.let { sms ->
            RemoteSmsDetailDialog(
                remoteSmsMessage = sms,
                onDismiss = { selectedSmsMessage = null }
            )
        }
    }
}

@Composable
fun RemoteSmsDetailDialog(
    remoteSmsMessage: RemoteSmsMessage,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "${remoteSmsMessage.getTypeDescription()} SMS (Synced)",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column {
                Text(
                    text = "From/To: ${remoteSmsMessage.getDisplayAddress()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Original Date: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date(remoteSmsMessage.date))}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Synced Date: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date(remoteSmsMessage.syncedAt))}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Telegram ID: ${remoteSmsMessage.remoteId}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "✅ Successfully synced to Telegram",
                    style = MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.ui.graphics.Color(0xFF4CAF50),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Message:",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = remoteSmsMessage.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
        modifier = modifier
    )
}
