package com.gilpereda.videomanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberApplicationState() =
    remember {
        VideoManagerApplicationState()
    }

class VideoManagerApplicationState
