package adapter.test

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Implementation of the ApplicationConfig interface for use in integrated tests
 */
class TestApplicationConfig : ApplicationConfig {
    private var videoFileExtensions: Set<String> = setOf()
    private var mediaSources: List<MediaSource> = emptyList()

    override val videoFileExtensionsFlow: MutableSharedFlow<Set<String>> = MutableStateFlow(videoFileExtensions)

    override val mediaSourcesFlow: MutableSharedFlow<List<MediaSource>> = MutableStateFlow(mediaSources)

    override suspend fun addVideoFileExtension(extension: String) {
        videoFileExtensions += extension
        emitVideoFileExtensions(videoFileExtensions)
    }

    override suspend fun removeVideoFileExtension(extension: String) {
        videoFileExtensions -= extension
        emitVideoFileExtensions(videoFileExtensions)
    }

    override suspend fun addMediaSource(mediaSource: MediaSource) {
        mediaSources += mediaSource
        emitMediaSources(mediaSources)
    }

    override suspend fun updateMediaSource(mediaSource: MediaSource) {
        mediaSources = mediaSources.map { if (it.id == mediaSource.id) mediaSource else it }
        emitMediaSources(mediaSources)
    }

    override suspend fun removeMediaSource(mediaSource: MediaSource) {
        mediaSources = mediaSources.filter { it.id != mediaSource.id }
        emitMediaSources(mediaSources)
    }

    private suspend fun emitVideoFileExtensions(extensions: Set<String>) {
        videoFileExtensions = extensions
        videoFileExtensionsFlow.emit(extensions)
    }

    private suspend fun emitMediaSources(mediaSources: List<MediaSource>) {
        this.mediaSources = mediaSources
        mediaSourcesFlow.emit(mediaSources)
    }
}
