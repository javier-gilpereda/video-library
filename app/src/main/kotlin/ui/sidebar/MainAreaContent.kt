package com.gilpereda.videomanager.ui.sidebar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import com.gilpereda.videomanager.ui.labels.actor.ActorManagementAreaView
import com.gilpereda.videomanager.ui.labels.producer.ProducerManagementAreaView
import com.gilpereda.videomanager.ui.labels.tag.TagManagementAreaView
import com.gilpereda.videomanager.ui.settings.SettingsAreaUI
import com.gilpereda.videomanager.ui.video.VideoManagementUI

sealed interface MainAreaContent {
    val name: String
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

const val VIDEO_MANAGEMENT = "video_management"

data object VideoManagement : MainAreaContent {
    override val name: String = VIDEO_MANAGEMENT
    override val icon: ImageVector = Icons.Outlined.Movie

    override val content: @Composable () -> Unit = { VideoManagementUI(Modifier.testTag(VIDEO_MANAGEMENT)) }
}

const val ACTOR_MANAGEMENT = "actor_management"

data object ActorManagement : MainAreaContent {
    override val name: String = ACTOR_MANAGEMENT
    override val icon: ImageVector = Icons.Outlined.Person

    override val content: @Composable () -> Unit = { ActorManagementAreaView(Modifier.testTag(ACTOR_MANAGEMENT)) }
}

const val PRODUCER_MANAGEMENT = "producer_management"

data object ProducerManagement : MainAreaContent {
    override val name: String = PRODUCER_MANAGEMENT
    override val icon: ImageVector = Icons.Outlined.Star

    override val content: @Composable () -> Unit = { ProducerManagementAreaView(Modifier.testTag(PRODUCER_MANAGEMENT)) }
}

const val TAG_MANAGEMENT = "tag_management"

data object TagManagement : MainAreaContent {
    override val name: String = TAG_MANAGEMENT
    override val icon: ImageVector = Icons.AutoMirrored.Outlined.Label

    override val content: @Composable () -> Unit = { TagManagementAreaView(Modifier.testTag(TAG_MANAGEMENT)) }
}

const val SETTINGS = "settings"

data object Settings : MainAreaContent {
    override val name: String = SETTINGS
    override val icon: ImageVector = Icons.Outlined.Settings

    override val content: @Composable () -> Unit = { SettingsAreaUI(Modifier.testTag(SETTINGS)) }
}
