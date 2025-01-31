package com.gilpereda.videomanager

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.gilpereda.videomanager.adapter.settings.PersistentApplicationConfig
import com.gilpereda.videomanager.di.ServiceRegistry
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

fun main(vararg args: String) =
    mainBody {
        val settings = ArgParser(args).parseInto(::PersistentApplicationConfig)
        ServiceRegistry.settings = settings
        application {
            Window(
                onCloseRequest = ::exitApplication,
                state = WindowState(width = 1600.dp, height = 1000.dp),
            ) {
                val applicationState = rememberApplicationState()
                VideoLibraryApplicationView(applicationState)
            }
        }
    }
