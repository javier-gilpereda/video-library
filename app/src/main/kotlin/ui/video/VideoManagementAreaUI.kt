package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.ui.components.PanelState
import com.gilpereda.videomanager.ui.components.VerticalSplittable

class VideoManagementState {
    val panelState = PanelState()
}

val LocalVideoManagementState = staticCompositionLocalOf { VideoManagementState() }

@Composable
fun VideoManagementUI(
    modifier: Modifier,
    videoManagementAreaViewModel: VideoManagementAreaViewModel = ServiceRegistry.videoManagementAreaViewModel,
) {
    val uiState by videoManagementAreaViewModel.uiState.collectAsState()
    VerticalSplittable(
        modifier = modifier.fillMaxHeight(),
        panelState = LocalVideoManagementState.current.panelState,
        leftPanel = {
            FolderTreeUI(
                uiState,
                videoManagementAreaViewModel::onFolderSelected,
                videoManagementAreaViewModel::onFolderExpandedToggled,
            )
        },
        rightPanel = {
            VideoListUI()
        },
    )
}
