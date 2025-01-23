package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.Video
import java.io.File

data class FSVideo(
    val file: File,
) : Video {
    override val name: String = file.name
}
