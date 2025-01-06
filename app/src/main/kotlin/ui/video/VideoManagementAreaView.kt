package com.gilpereda.videomanager.ui.videomanagement

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.gilpereda.videomanager.ui.components.PanelState
import com.gilpereda.videomanager.ui.components.VerticalSplittable
import com.gilpereda.videomanager.ui.video.FileTreeView
import com.gilpereda.videomanager.ui.video.VideoListView

class VideoManagementState {
    val panelState = PanelState()
}

val LocalVideoManagementState = staticCompositionLocalOf { VideoManagementState() }

@Composable
fun VideoManagementAreaView() {
    VerticalSplittable(
        modifier = Modifier.fillMaxHeight(),
        panelState = LocalVideoManagementState.current.panelState,
        leftPanel = { FileTreeView() },
        rightPanel = { VideoListView() },
    )
}
