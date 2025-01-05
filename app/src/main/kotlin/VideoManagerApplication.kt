package com.gilpereda.videomanager

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import com.gilpereda.videomanager.ui.VideoManagerView
import com.gilpereda.videomanager.ui.common.Theme

@Composable
fun ApplicationScope.VideoManagerApplication() {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 1600.dp, height = 1000.dp),
    ) {
        val applicationState = rememberApplicationState()
        VideoManagerApplicationView(applicationState)
    }
}

@Composable
fun VideoManagerApplicationView(applicationState: VideoManagerApplicationState) {
//    val theme = if (isSystemInDarkTheme()) Theme.dark else Theme.light
    val theme = Theme.dark

    MaterialTheme(
        colorScheme = theme.colorScheme,
    ) {
        Surface {
            VideoManagerView(applicationState)
//            ColorPalette(Theme.dark.colorScheme)
        }
    }
}
