package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import kotlinx.coroutines.flow.StateFlow

interface ApplicationSettings {
    val defaultFilter: VideoFilter
    val mediaSources: List<MediaSource>

    fun <T> getStateFlow(
        key: String,
        initialValue: T,
    ): StateFlow<T>

    operator fun <T> set(
        key: String,
        value: T,
    )
}
