package com.gilpereda.videomanager.ui.sidebar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.gilpereda.videomanager.ui.labels.actor.ActorManagementAreaView
import com.gilpereda.videomanager.ui.labels.producer.ProducerManagementAreaView
import com.gilpereda.videomanager.ui.labels.tag.TagManagementAreaView
import com.gilpereda.videomanager.ui.settings.SettingsAreaView
import com.gilpereda.videomanager.ui.videomanagement.VideoManagementAreaView

sealed interface MainAreaContent {
    val icon: ImageVector
    val content: @Composable () -> Unit

    companion object {
        val areas =
            listOf(
                VideoManagement,
                ActorManagement,
                ProducerManagement,
                TagManagement,
                Settings,
            )
    }
}

data object VideoManagement : MainAreaContent {
    override val icon: ImageVector = Icons.Outlined.Movie

    override val content: @Composable () -> Unit = { VideoManagementAreaView() }
}

data object ActorManagement : MainAreaContent {
    override val icon: ImageVector = Icons.Outlined.Person

    override val content: @Composable () -> Unit = { ActorManagementAreaView() }
}

data object ProducerManagement : MainAreaContent {
    override val icon: ImageVector = Icons.Outlined.Star

    override val content: @Composable () -> Unit = { ProducerManagementAreaView() }
}

data object TagManagement : MainAreaContent {
    override val icon: ImageVector = Icons.AutoMirrored.Outlined.Label

    override val content: @Composable () -> Unit = { TagManagementAreaView() }
}

data object Settings : MainAreaContent {
    override val icon: ImageVector = Icons.Outlined.Settings

    override val content: @Composable () -> Unit = { SettingsAreaView() }
}
