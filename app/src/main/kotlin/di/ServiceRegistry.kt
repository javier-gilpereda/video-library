package com.gilpereda.videomanager.di

import com.gilpereda.videomanager.adapter.filesystem.FSVideoFileRepository
import com.gilpereda.videomanager.service.MediaLibraryManager
import com.gilpereda.videomanager.service.ports.ApplicationSettings
import com.gilpereda.videomanager.service.ports.VideoFileRepository
import com.gilpereda.videomanager.ui.video.VideoManagementAreaViewModel

object ServiceRegistry {
    lateinit var settings: ApplicationSettings

    private val videoFileRepository: VideoFileRepository by lazy {
        FSVideoFileRepository()
    }

    val mediaLibraryManager: MediaLibraryManager by lazy {
        MediaLibraryManager(settings, videoFileRepository)
    }

    val videoManagementAreaViewModel: VideoManagementAreaViewModel by lazy {
        VideoManagementAreaViewModel(mediaLibraryManager, settings)
    }
}
