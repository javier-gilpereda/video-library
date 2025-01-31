package com.gilpereda.videomanager.adapter.settings

import androidx.datastore.core.Serializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.gilpereda.videomanager.domain.AppSettings
import java.io.InputStream
import java.io.OutputStream

val VIDEO_EXTENSIONS =
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

class SettingsSerializer(
    private val objectMapper: ObjectMapper,
) : Serializer<AppSettings> {
    override val defaultValue: AppSettings =
        AppSettings(
            videoFileExtensions = VIDEO_EXTENSIONS,
            mediaSources = emptyList(),
        )

    override suspend fun readFrom(input: InputStream): AppSettings = objectMapper.readValue(input)

    override suspend fun writeTo(
        t: AppSettings,
        output: OutputStream,
    ) {
        objectMapper.writeValue(output, t)
    }
}
