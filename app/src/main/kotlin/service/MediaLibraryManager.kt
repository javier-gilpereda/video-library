package com.gilpereda.videomanager.service

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationSettings
import com.gilpereda.videomanager.service.ports.VideoFileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MediaLibraryManager(
    private val applicationSettings: ApplicationSettings,
    private val videoFileRepository: VideoFileRepository,
) {
    val mediaLibrarySource: StateFlow<List<Folder>> =
        MutableStateFlow(applicationSettings.mediaSources.map(videoFileRepository::loadMediaSource))

    fun listVideos(
        folder: Folder,
        filter: VideoFilter = VideoFilter.Noop,
    ): List<Video> = videoFileRepository.findVideos(folder, applicationSettings.defaultFilter + filter)
}
