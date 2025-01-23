package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column {
                TreeTitleView()
                FolderTreeUI(
                    uiState,
                    videoManagementAreaViewModel::onFolderSelected,
                    videoManagementAreaViewModel::onFolderExpandedToggled,
                )
            }
        },
        rightPanel = {
            Column {
                VideoListUI(
                    uiState.videoListUIState,
                    videoManagementAreaViewModel::onVideoSelectedToggled,
                )
            }
        },
    )
}

@Composable
fun TreeTitleView() =
    Surface {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "Videos",
                color = LocalContentColor.current.copy(alpha = 0.60f),
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 4.dp).fillMaxWidth(),
            )
        }
    }
