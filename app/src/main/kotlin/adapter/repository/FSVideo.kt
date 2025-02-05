package com.gilpereda.videomanager.adapter.repository

import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoMetadata
import java.io.File

data class FSVideo(
    val file: File,
) : Video {
    override val name: String = file.name
    override val videoMetadata: VideoMetadata? = null
    override val thumbnail: String? = null
}
