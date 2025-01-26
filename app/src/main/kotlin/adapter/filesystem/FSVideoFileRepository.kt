package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.VideoFileRepository

class FSVideoFileRepository : VideoFileRepository {
    override fun loadMediaSource(source: MediaSource): Folder = FSFolder.loadTree(source)

    // TODO: use an index to list the files
    override fun findVideos(
        folder: Folder,
        filter: VideoFilter,
    ): List<Video> = (folder as FSFolder).videos(filter)
}
