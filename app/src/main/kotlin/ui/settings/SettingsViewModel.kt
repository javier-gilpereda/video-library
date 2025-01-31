package com.gilpereda.videomanager.ui.settings

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import kotlinx.coroutines.flow.Flow

class SettingsViewModel(
    private val settings: ApplicationConfig,
) {
    val mediaSourcesFlow: Flow<List<MediaSource>> = settings.mediaSourcesFlow
    val defaultFilterFlow: Flow<VideoFilter> = settings.defaultFilterFlow

    suspend fun onMediaSourceAdded(mediaSource: MediaSource) {
        settings.addMediaSource(mediaSource)
    }

    suspend fun onMediaSourceRemoved(mediaSource: MediaSource) {
        settings.removeMediaSource(mediaSource)
    }

    suspend fun onMediaSourceUpdated(mediaSource: MediaSource) {
        settings.updateMediaSource(mediaSource)
    }

    suspend fun onUpdateVideoExtensions(extensions: Set<String>) {
        settings.updateVideoExtensions(extensions)
    }
}
