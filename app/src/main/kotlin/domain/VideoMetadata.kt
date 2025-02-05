package com.gilpereda.videomanager.domain

import kotlin.time.Duration

data class VideoMetadata(
    val aspectRatio: String,
    val duration: Duration,
    val format: String,
    val formatLong: String,
    val framesRate: Float?,
    val height: Int,
    val size: Long,
    val width: Int,
)
