package com.gilpereda.videomanager.service

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.service.ports.ApplicationSettings
import com.gilpereda.videomanager.service.ports.VideoFileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MediaLibraryManager(
    applicationSettings: ApplicationSettings,
    val videoFileRepository: VideoFileRepository,
) {
    val mediaLibrarySource: StateFlow<List<Folder>> =
        MutableStateFlow(applicationSettings.mediaSources.map(videoFileRepository::loadMediaSource))
}
