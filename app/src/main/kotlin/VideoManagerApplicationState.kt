package com.gilpereda.videomanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gilpereda.videomanager.ui.sidebar.MainAreaContent
import com.gilpereda.videomanager.ui.sidebar.VideoManagement

@Composable
fun rememberApplicationState() =
    remember {
        VideoManagerApplicationState()
    }

class VideoManagerApplicationState {
    var activeTab: MainAreaContent by mutableStateOf(VideoManagement)
}
