package com.gilpereda.videomanager.adapter.settings

import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.VideoFilter
import com.gilpereda.videomanager.service.ports.ApplicationSettings
import com.xenomachina.argparser.ArgParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val MEDIA_SOURCES = "media_sources"

private const val CREATE_SCHEMA = """
CREATE TABLE IF NOT EXISTS $MEDIA_SOURCES (name string, path string);
"""

private val VIDEO_EXTENSIONS =
    setOf(
        "avi",
        "mov",
        "mpg",
        "mpeg",
        "mkv",
        "mp4",
        "flv",
        "wmv",
    )

class PersistentApplicationSettings(
    parser: ArgParser,
) : ApplicationSettings {
    private val regular = mutableMapOf<String, Any?>()
    private val flows = mutableMapOf<String, MutableStateFlow<Any?>>()

    private val workingDirectory: String by parser.storing(
        "-d",
        "--directory",
        help = "The working directory for the application",
//        transform = { "$this/.video-library" },
    )

//    private val connectionString: String by lazy {
//        "jdbc:sqlite:$workingDirectory/db/settings.db"
//    }
//
//    private val dbConnection: Connection by lazy {
//        DriverManager
//            .getConnection(connectionString)
//            .also { it.createStatement().executeUpdate(CREATE_SCHEMA) }
//    }

    override val defaultFilter: VideoFilter = VideoFilter.Extension(VIDEO_EXTENSIONS)

    override val mediaSources: List<MediaSource> =
        listOf(MediaSource(name = "root", path = workingDirectory))

    @Suppress("UNCHECKED_CAST")
    override fun <T> getStateFlow(
        key: String,
        initialValue: T,
    ): StateFlow<T> =
        flows.computeIfAbsent(key) {
            MutableStateFlow(regular.computeIfAbsent(key) { initialValue })
        } as StateFlow<T>

    override fun <T> set(
        key: String,
        value: T,
    ) {
        regular[key] = value
        flows[key]?.value = value
    }
}
