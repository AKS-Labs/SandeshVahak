package com.akslabs.chitralaya.ui.onboarding // Corrected package declaration

import androidx.activity.compose.BackHandler
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
import com.akslabs.chitralaya.R // Corrected R import
import com.akslabs.chitralaya.api.BotApi // Corrected BotApi import
import com.akslabs.chitralaya.ui.onboarding.DisclaimerScreen // Added import
import com.akslabs.chitralaya.ui.onboarding.GettingStartedScreen // Added import

import kotlinx.coroutines.delay

@Preview
@Composable
fun OnboardingPage(
    onOnboardingComplete: () -> Unit = {},
    modifier: Modifier = Modifier,
    botApi: BotApi = BotApi
) {
    var showDisclaimer by remember { mutableStateOf(true) }
    var showGettingStarted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        botApi.create()
        botApi.startPolling()
    }

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
                // Handle back navigation from GettingStartedScreen to DisclaimerScreen
                BackHandler {
                    showGettingStarted = false
                    showDisclaimer = true
                }

                GettingStartedScreen(
                    onProceed = {
                        showGettingStarted = false
                        onOnboardingComplete()
                    },
                    onBack = {
                        showGettingStarted = false
                        showDisclaimer = true
                    },
                    modifier = modifier,
                    botApi = botApi
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingPagePreview() {
    OnboardingPage()
}