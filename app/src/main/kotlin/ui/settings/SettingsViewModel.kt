package com.gilpereda.videomanager.ui.settings

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import kotlinx.coroutines.flow.Flow

class SettingsViewModel(
    private val settings: ApplicationConfig,
) {
    val mediaSourcesFlow: Flow<List<MediaSource>> = settings.mediaSourcesFlow
    val videoFileExtensionsFlow: Flow<Set<String>> = settings.videoFileExtensionsFlow

    suspend fun onMediaSourceAdded(mediaSource: MediaSource) {
        settings.addMediaSource(mediaSource)
    }

    suspend fun onMediaSourceRemoved(mediaSource: MediaSource) {
        settings.removeMediaSource(mediaSource)
    }

    suspend fun onMediaSourceUpdated(mediaSource: MediaSource) {
        settings.updateMediaSource(mediaSource)
    }

    suspend fun onVideoFileExtensionAdded(extension: String) {
        settings.addVideoFileExtension(extension)
    }

    suspend fun onVideoFileExtensionRemoved(extension: String) {
        settings.removeVideoFileExtension(extension)
    }
}
