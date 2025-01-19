package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.MediaSource
import kotlinx.coroutines.flow.StateFlow

interface ApplicationSettings {
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
