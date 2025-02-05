package com.gilpereda.videomanager.adapter.ffmpeg

import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoMetadata
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import com.gilpereda.videomanager.service.ports.VideoMetadataResolver

class FfmpegService(
    private val appSettings: ApplicationConfig = ServiceRegistry.settings,
) : VideoMetadataResolver {
    override fun resolveMetadata(video: Video): VideoMetadata {
        TODO()
    }
}
