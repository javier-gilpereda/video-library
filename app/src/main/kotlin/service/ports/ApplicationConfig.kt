package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.MediaSource
import kotlinx.coroutines.flow.Flow

interface ApplicationConfig {
    val videoFileExtensionsFlow: Flow<Set<String>>

    val mediaSourcesFlow: Flow<List<MediaSource>>

    suspend fun addVideoFileExtension(extension: String)

    suspend fun removeVideoFileExtension(extension: String)

    suspend fun addMediaSource(mediaSource: MediaSource)

    suspend fun updateMediaSource(mediaSource: MediaSource)

    suspend fun removeMediaSource(mediaSource: MediaSource)
}
