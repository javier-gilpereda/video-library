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
    private val _defaultFilterFlow: MutableSharedFlow<VideoFilter> = MutableStateFlow(VideoFilter.Noop)
    private val _mediaSourcesFlow: MutableSharedFlow<List<MediaSource>> = MutableStateFlow(emptyList())

    override val defaultFilter: VideoFilter = VideoFilter.Noop
    override val defaultFilterFlow: Flow<VideoFilter> = _defaultFilterFlow
    override val mediaSourcesFlow: Flow<List<MediaSource>> = _mediaSourcesFlow

    suspend fun emitDefaultFilter(filter: VideoFilter) {
        _defaultFilterFlow.emit(filter)
    }

    suspend fun emitMediaSources(mediaSources: List<MediaSource>) {
        _mediaSourcesFlow.emit(mediaSources)
    }
}
