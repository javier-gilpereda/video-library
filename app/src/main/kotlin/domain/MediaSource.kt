package com.gilpereda.videomanager.domain

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class MediaSource(
    val id: String,
    val name: String,
    val path: String,
) {
    companion object {
        fun create(
            name: String,
            path: String,
        ): MediaSource = MediaSource(Uuid.random().toString(), name, path)
    }
}
