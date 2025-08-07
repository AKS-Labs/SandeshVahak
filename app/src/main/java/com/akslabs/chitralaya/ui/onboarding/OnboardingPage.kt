package com.akslabs.Suchak.ui.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.akslabs.Suchak.R
import com.akslabs.Suchak.api.BotApi
import com.akslabs.Suchak.ui.components.NoInternetScreen
import com.akslabs.Suchak.utils.connectivity.ConnectivityObserver
import com.akslabs.Suchak.utils.connectivity.ConnectivityStatus
import kotlinx.coroutines.delay

@Preview
@Composable
fun OnboardingPage(
    onOnboardingComplete: () -> Unit = {},
    modifier: Modifier = Modifier,
    connectivityObserver: ConnectivityObserver = ConnectivityObserver,
    botApi: BotApi = BotApi
) {
    val connectivityStatus by connectivityObserver.observe().collectAsState(initial = ConnectivityStatus.Unavailable)
    var showDisclaimer by remember { mutableStateOf(true) }
    var showGettingStarted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        botApi.create()
        botApi.startPolling()
    }

    when (connectivityStatus) {
        ConnectivityStatus.Available -> {
            AnimatedContent(
                targetState = when {
                    showDisclaimer -> "disclaimer"
                    showGettingStarted -> "getting_started"
                    else -> "complete"
                },
                label = "onboarding_content"
            ) { state ->
                when (state) {
                    "disclaimer" -> {
                        DisclaimerScreen(
                            onAcknowledge = {
                                showDisclaimer = false
                                showGettingStarted = true
                            },
                            modifier = modifier
                        )
                    }
                    "getting_started" -> {
                        GettingStartedScreen(
                            onProceed = {
                                showGettingStarted = false
                                onOnboardingComplete()
                            },
                            modifier = modifier,
                            botApi = botApi
                        )
                    }
                }
            }
        }
        ConnectivityStatus.Unavailable -> {
            NoInternetScreen(
                modifier = modifier,
                isConnected = false
            )
        }
    }
}

@Preview
@Composable
fun OnboardingPagePreview() {
    OnboardingPage()
}