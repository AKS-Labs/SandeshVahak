package com.akslabs.SandeshVahak.ui.onboarding

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.api.BotApi
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.github.kotlintelegrambot.entities.ChatId

import kotlinx.coroutines.launch

@Composable
fun UidComponent(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onNavigate: () -> Unit,
    botApi: BotApi = BotApi,
) {
    var inputIdState by remember { mutableStateOf("") }
    var isValidInput by remember { mutableStateOf(true) }
    var showStepsDisclaimer by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        botApi.create()
        botApi.startPolling()
    }
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = remember { DialogProperties(usePlatformDefaultWidth = false) }
    ) {
        AnimatedVisibility(visible = showStepsDisclaimer) {
            AlertDialog(
                onDismissRequest = {},
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = stringResource(id = R.string.app_icon)
                    )
                },
                confirmButton = {
                    TextButton(onClick = { showStepsDisclaimer = false }) {
                        Text(
                            text = stringResource(id = R.string.got_it),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                title = { Text(text = stringResource(R.string.just_a_few_more)) },
                text = {
                    Column {
                        Text(text = stringResource(R.string._1_create_a_private_group_on_telegram))
                        Text(text = stringResource(R.string._2_add_the_bot_to_the_group))
                        Text(text = stringResource(R.string._3_type_start_in_the_group))
                        Text(text = stringResource(R.string._4_copy_and_paste_the_unique_id_here))
                        Text(text = stringResource(id = R.string._5_click_on_proceed))
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = inputIdState,
                    onValueChange = { inputIdState = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = !isValidInput,
                    supportingText = {
                        AnimatedContent(
                            targetState = isValidInput,
                            label = stringResource(R.string.supporttext)
                        ) {
                            Column {
                                if (!it) {
                                    Text(
                                        text = stringResource(R.string.invalid_uid_or_token),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                } else {
                                    Text(
                                        text = stringResource(
                                            R.string.can_be_ve_or_ve_e_g_1234567890_do_not_ignore_the_sign
                                        ),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors().copy(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    label = { Text(text = stringResource(R.string.unique_id_start_in_gc)) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    textStyle = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .width(300.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        scope.launch {
                            try {
                                Log.i("UidComponent", "Attempting to validate chat ID: $inputIdState")
                                val id = inputIdState.toLongOrNull()
                                if (id != null) {
                                    Log.i("UidComponent", "Parsed chat ID: $id (${if (id < 0) "group/channel" else "bot/user"})")

                                    // Check if bot can access the chat (works for both groups and direct chats)
                                    Log.i("UidComponent", "Checking bot access to chat ID: $id")
                                    val canAccess = botApi.getChat(ChatId.fromId(id))
                                    Log.i("UidComponent", "Bot access result: $canAccess")

                                    if (canAccess) {
                                        // verification successful
                                        Log.i("UidComponent", "Chat ID verification successful, saving to preferences")
                                        Preferences.editEncrypted { putLong(Preferences.channelId, id) }
                                        botApi.stopPolling()
                                        Log.i("UidComponent", "Successfully configured chat ID: $id")
                                        // navigate to main screen
                                        onNavigate()
                                    } else {
                                        Log.w("UidComponent", "Bot cannot access chat ID: $id")
                                        isValidInput = false
                                    }
                                } else {
                                    Log.w("UidComponent", "Invalid chat ID format: $inputIdState")
                                    isValidInput = false
                                }
                            } catch (e: Exception) {
                                Log.e("UidComponent", "Error validating chat ID: $inputIdState", e)
                                isValidInput = false
                            }
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.proceed))
                }
            }
        }
    }
}