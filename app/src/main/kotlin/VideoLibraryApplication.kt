package com.gilpereda.videomanager

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.gilpereda.videomanager.ui.MainMediaLibraryView
import com.gilpereda.videomanager.ui.common.Theme
import com.gilpereda.videomanager.ui.components.PanelState
import com.gilpereda.videomanager.ui.sidebar.MainAreaContent
import com.gilpereda.videomanager.ui.sidebar.VideoManagement
import com.gilpereda.videomanager.ui.video.LocalVideoManagementState
import com.gilpereda.videomanager.ui.video.LocalVideoPreviewPanelState
import com.gilpereda.videomanager.ui.video.VideoManagementState

@Composable
fun rememberApplicationState() =
    remember {
        VideoLibraryApplicationState()
    }

class VideoLibraryApplicationState {
    val activeTab: MutableState<MainAreaContent> = mutableStateOf(VideoManagement)
}

@Composable
fun VideoLibraryApplicationView(applicationState: VideoLibraryApplicationState) {
//    val theme = if (isSystemInDarkTheme()) Theme.dark else Theme.light
    val theme = Theme.dark
    val videoManagementState = VideoManagementState()
    val videoPreviewPanelState = PanelState()

    CompositionLocalProvider(
        LocalVideoManagementState provides videoManagementState,
        LocalVideoPreviewPanelState provides videoPreviewPanelState,
    ) {
        MaterialTheme(
            colorScheme = theme.colorScheme,
        ) {
            Surface {
                MainMediaLibraryView(applicationState)
//                ColorPalette(MaterialTheme.colorScheme)
            }
        }
    }
}
