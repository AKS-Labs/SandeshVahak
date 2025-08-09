package com.akslabs.chitralaya.ui.main.screens.local

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.akslabs.SandeshVahak.R
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.SandeshVahak.ui.components.LoadAnimation
import com.akslabs.chitralaya.ui.components.SmsListItem
import com.akslabs.chitralaya.ui.components.itemsPaging

private const val TAG = "LocalSmsScreen"

@Composable
fun LocalSmsScreen(
    localSmsMessages: LazyPagingItems<SmsMessage>,
    totalCount: Int,
    modifier: Modifier = Modifier
) {
    var selectedSmsMessage by remember { mutableStateOf<SmsMessage?>(null) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Debug recomposition
        SideEffect { Log.d(TAG, "Recompose LocalSmsScreen: items=${localSmsMessages.itemCount}, loadState=${localSmsMessages.loadState}") }
        when {
            localSmsMessages.loadState.refresh == LoadState.Loading -> {
                LoadAnimation(modifier = Modifier.align(Alignment.Center))
            }

            localSmsMessages.itemCount == 0 && localSmsMessages.loadState.refresh is LoadState.NotLoading -> {
                // Empty state
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No SMS messages found",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "SMS messages will appear here once you grant SMS permissions and receive messages",
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
                        items = localSmsMessages,
                        key = { it.id }
                    ) { smsMessage, _ ->
                        smsMessage?.let {
                            SmsListItem(
                                smsMessage = it,
                                onClick = { selectedSmsMessage = it }
                            )
                        }
                    }
                }
            }
        }

        // SMS detail dialog
        selectedSmsMessage?.let { sms ->
            SmsDetailDialog(
                smsMessage = sms,
                onDismiss = { selectedSmsMessage = null }
            )
        }
    }
}

@Composable
fun SmsDetailDialog(
    smsMessage: SmsMessage,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "${smsMessage.getTypeDescription()} SMS",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column {
                Text(
                    text = "From/To: ${smsMessage.getDisplayAddress()}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Date: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date(smsMessage.date))}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                if (smsMessage.isSynced) {
                    Text(
                        text = "✅ Synced to Telegram",
                        style = MaterialTheme.typography.bodyMedium,
                        color = androidx.compose.ui.graphics.Color(0xFF4CAF50),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                } else if (smsMessage.syncError != null) {
                    Text(
                        text = "❌ Sync failed: ${smsMessage.syncError}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = androidx.compose.ui.graphics.Color(0xFFF44336),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                } else {
                    Text(
                        text = "⏳ Pending sync",
                        style = MaterialTheme.typography.bodyMedium,
                        color = androidx.compose.ui.graphics.Color(0xFFFF9800),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                Text(
                    text = "Message:",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = smsMessage.body,
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
