package com.gilpereda.videomanager.domain

interface Video {
    val name: String
    val videoMetadata: VideoMetadata?
    val thumbnail: String?
}
