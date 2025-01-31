package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import kotlinx.coroutines.flow.Flow

interface ApplicationConfig {
    val defaultFilter: VideoFilter
    val defaultFilterFlow: Flow<VideoFilter>
    val mediaSourcesFlow: Flow<List<MediaSource>>

    suspend fun updateVideoExtensions(extensions: Set<String>)

    suspend fun addMediaSource(mediaSource: MediaSource)

    suspend fun updateMediaSource(mediaSource: MediaSource)

    suspend fun removeMediaSource(mediaSource: MediaSource)
}
