package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.service.ports.VideoFileRepository

class FSVideoFileRepository : VideoFileRepository {
    override fun loadMediaSource(source: MediaSource): Folder = FSFolder.loadTree(source)
}
