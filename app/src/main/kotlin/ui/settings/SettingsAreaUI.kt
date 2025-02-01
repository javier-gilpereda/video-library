package com.gilpereda.videomanager.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.ui.common.H1

@Composable
fun SettingsAreaUI(
    modifier: Modifier,
    settingsViewModel: SettingsViewModel = ServiceRegistry.settingsViewModel,
) {
    Surface {
        Box(
            modifier = modifier.fillMaxSize().padding(40.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                H1(text = "Media Sources", modifier = Modifier.fillMaxWidth())
                MediaSourcesUI(
                    mediaSourcesFlow = settingsViewModel.mediaSourcesFlow,
                    onMediaSourceAdded = settingsViewModel::onMediaSourceAdded,
                    onMediaSourceRemoved = settingsViewModel::onMediaSourceRemoved,
                    onMediaSourceUpdated = settingsViewModel::onMediaSourceUpdated,
                )

                H1(text = "Video files extensions", modifier = Modifier.fillMaxWidth())
                VideoFileExtensionsUI(
                    videoFileExtensionsFlow = settingsViewModel.videoFileExtensionsFlow,
                    onExtensionAdded = settingsViewModel::onVideoFileExtensionAdded,
                    onExtensionRemoved = settingsViewModel::onVideoFileExtensionRemoved,
                )
            }
        }
    }
}
