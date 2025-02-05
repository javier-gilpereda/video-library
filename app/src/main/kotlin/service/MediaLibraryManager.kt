package com.gilpereda.videomanager.service

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import com.gilpereda.videomanager.service.ports.VideoFileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MediaLibraryManager(
    applicationConfig: ApplicationConfig,
    private val videoFileRepository: VideoFileRepository,
) {
    val mediaLibrarySource: Flow<List<Folder>> =
        applicationConfig.mediaSourcesFlow.map { sources -> sources.map(videoFileRepository::loadMediaSource) }

    fun listVideos(
        folder: Folder,
        filter: VideoFilter? = null,
    ): List<Video> = videoFileRepository.findVideos(folder, filter)
}
