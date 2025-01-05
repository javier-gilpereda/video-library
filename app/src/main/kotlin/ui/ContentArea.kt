package com.gilpereda.videomanager.ui

import androidx.compose.runtime.Composable
import com.gilpereda.videomanager.VideoManagerApplicationState

@Composable
fun ContentArea(applicationState: VideoManagerApplicationState) {
    applicationState.activeTab.content()
}
