package com.gilpereda.videomanager.di

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gilpereda.videomanager.adapter.filesystem.FSVideoFileRepository
import com.gilpereda.videomanager.adapter.settings.SettingsSerializer
import com.gilpereda.videomanager.service.MediaLibraryManager
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import com.gilpereda.videomanager.service.ports.VideoFileRepository
import com.gilpereda.videomanager.ui.settings.SettingsViewModel
import com.gilpereda.videomanager.ui.video.VideoManagementAreaViewModel

object ServiceRegistry {
    lateinit var settings: ApplicationConfig

    private val videoFileRepository: VideoFileRepository by lazy {
        FSVideoFileRepository()
    }

    val mediaLibraryManager: MediaLibraryManager by lazy {
        MediaLibraryManager(settings, videoFileRepository)
    }

    val videoManagementAreaViewModel: VideoManagementAreaViewModel by lazy {
        VideoManagementAreaViewModel(mediaLibraryManager)
    }

    val settingsViewModel: SettingsViewModel by lazy {
        SettingsViewModel(settings)
    }

    val settingsSerializer: SettingsSerializer by lazy {
        SettingsSerializer(objectMapper)
    }

    private val objectMapper: ObjectMapper by lazy {
        jacksonObjectMapper()
    }
}
