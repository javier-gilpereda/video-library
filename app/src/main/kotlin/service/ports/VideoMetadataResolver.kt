package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoMetadata

interface VideoMetadataResolver {
    fun resolveMetadata(video: Video): VideoMetadata
}
