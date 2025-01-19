package com.gilpereda.videomanager.service.ports

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource

interface VideoFileRepository {
    fun loadMediaSource(source: MediaSource): Folder
}
