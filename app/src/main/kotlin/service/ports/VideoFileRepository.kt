package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoFilter

interface VideoFileRepository {
    fun loadMediaSource(source: MediaSource): Folder

    fun findVideos(
        folder: Folder,
        filter: VideoFilter?,
    ): List<Video>
}
