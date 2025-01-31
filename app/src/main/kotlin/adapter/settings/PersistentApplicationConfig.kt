package com.gilpereda.videomanager.adapter.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.domain.AppSettings
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import com.xenomachina.argparser.ArgParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.uuid.ExperimentalUuidApi

private const val APPLICATION_DATA_SUB_DIR = ".video-library"
private const val SETTINGS_FILE = "settings.db"

@OptIn(ExperimentalUuidApi::class)
class PersistentApplicationConfig(
    parser: ArgParser,
) : ApplicationConfig {
    private val workingDirectory: String by parser.storing(
        "-d",
        "--directory",
        help = "The working directory for the application",
    )

    private val applicationDataDirectory: String by lazy {
        "${workingDirectory.removeSuffix("/")}/$APPLICATION_DATA_SUB_DIR"
            .also {
                eventuallyCreateDirectory(Paths.get(it))
            }
    }

    private val settingsFile: File by lazy {
        File("$applicationDataDirectory/$SETTINGS_FILE")
    }

    private val appSettingsStore: DataStore<AppSettings> by lazy {
        DataStoreFactory.create(ServiceRegistry.settingsSerializer) { settingsFile }
    }

    override val defaultFilter: VideoFilter = VideoFilter.Extension(VIDEO_EXTENSIONS)

    override val defaultFilterFlow: Flow<VideoFilter> =
        appSettingsStore.data.map { VideoFilter.Extension(it.videoFileExtensions) }

    override val mediaSourcesFlow: Flow<List<MediaSource>> =
        appSettingsStore.data.map { it.mediaSources }

    override suspend fun updateVideoExtensions(extensions: Set<String>) {
        appSettingsStore.updateData { currentSettings ->
            currentSettings.copy(videoFileExtensions = extensions)
        }
    }

    override suspend fun addMediaSource(mediaSource: MediaSource) {
        appSettingsStore.updateData { currentSettings ->
            val currentMediaSources = currentSettings.mediaSources
            if (currentMediaSources.any { it.id == mediaSource.id }) {
                currentSettings
            } else {
                currentSettings.copy(mediaSources = currentMediaSources + mediaSource)
            }
        }
    }

    override suspend fun updateMediaSource(mediaSource: MediaSource) {
        appSettingsStore.updateData { currentSettings ->
            val currentMediaSources = currentSettings.mediaSources
            val updatedMediaSources =
                currentMediaSources.map {
                    if (it.id == mediaSource.id) mediaSource else it
                }
            currentSettings.copy(mediaSources = updatedMediaSources)
        }
    }

    override suspend fun removeMediaSource(mediaSource: MediaSource) {
        appSettingsStore.updateData { currentSettings ->
            val currentMediaSources = currentSettings.mediaSources
            currentSettings.copy(mediaSources = currentMediaSources - mediaSource)
        }
    }

    private fun eventuallyCreateDirectory(path: Path) {
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            Files.createDirectory(path)
        }
    }
}
