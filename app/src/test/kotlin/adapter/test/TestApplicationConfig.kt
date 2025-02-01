package adapter.test

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Implementation of the ApplicationConfig interface for use in integrated tests
 */
class TestApplicationConfig : ApplicationConfig {
    private var videoFileExtensions: Set<String> = setOf()
    private var mediaSources: List<MediaSource> = emptyList()
    private val _videoFileExtensionsFlow: MutableSharedFlow<Set<String>> = MutableStateFlow(videoFileExtensions)
    private val _mediaSourcesFlow: MutableSharedFlow<List<MediaSource>> = MutableStateFlow(mediaSources)

    override val defaultFilter: VideoFilter = VideoFilter.Noop
    override val videoFileExtensionsFlow: Flow<Set<String>> = _videoFileExtensionsFlow
    override val mediaSourcesFlow: Flow<List<MediaSource>> = _mediaSourcesFlow

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
        _videoFileExtensionsFlow.emit(extensions)
    }

    private suspend fun emitMediaSources(mediaSources: List<MediaSource>) {
        _mediaSourcesFlow.emit(mediaSources)
    }
}
