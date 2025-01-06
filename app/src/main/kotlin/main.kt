package com.gilpereda.videomanager

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = WindowState(width = 1600.dp, height = 1000.dp),
        ) {
            val applicationState = rememberApplicationState()
            VideoLibraryApplicationView(applicationState)
        }
    }
