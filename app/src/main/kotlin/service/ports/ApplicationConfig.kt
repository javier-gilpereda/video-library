package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import kotlinx.coroutines.flow.Flow

interface ApplicationConfig {
    val defaultFilter: VideoFilter
    val videoFileExtensionsFlow: Flow<Set<String>>
    val mediaSourcesFlow: Flow<List<MediaSource>>

    suspend fun addVideoFileExtension(extension: String)

    suspend fun removeVideoFileExtension(extension: String)

    suspend fun addMediaSource(mediaSource: MediaSource)

    suspend fun updateMediaSource(mediaSource: MediaSource)

    suspend fun removeMediaSource(mediaSource: MediaSource)
}
