package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gilpereda.videomanager.domain.Video

@Composable
fun VideoPreviewUI(video: Video) {
    Column(
        Modifier.fillMaxSize(),
    ) {
        Text(text = video.name)
    }
}
