package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.Video
import java.io.File
import java.io.FileFilter

private val MOVIE_EXTENSIONS =
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

private val filterFilesOfInterest =
    FileFilter { file ->
        !file.name.startsWith(".") && (file.isDirectory || file.extension in MOVIE_EXTENSIONS)
    }

sealed class FSFolder : Folder {
    protected abstract val file: File

    // TODO make it updatable
    val videos: List<Video> by lazy {
        file
            .listFiles(filterFilesOfInterest)
            .orEmpty()
            .map { FSVideo(it) }
    }

    private data class Root(
        override val name: String,
        override val file: File,
    ) : FSFolder() {
        override val id: String = file.absolutePath
        override val children: List<FSFolder> by lazy {
            file
                .listFiles { file: File -> file.isDirectory }
                .orEmpty()
                .map { Node(it, this) }
        }
    }

    private data class Node(
        override val file: File,
        val parent: FSFolder,
    ) : FSFolder() {
        override val name: String = file.name
        override val children: List<FSFolder> by lazy {
            file
                .listFiles { file: File -> file.isDirectory }
                .orEmpty()
                .map { Node(file = it, parent = this) }
        }

        override val id: String = file.absolutePath
    }

    companion object {
        fun loadTree(mediaSource: MediaSource): Folder =
            File(mediaSource.path)
                .let {
                    when {
                        !it.exists() -> throw IllegalStateException("Root directory ${mediaSource.path} does not exists")
                        !it.isDirectory -> throw IllegalStateException("Root directory ${mediaSource.path} is not a directory")
                        else -> Root(name = mediaSource.name, file = it)
                    }
                }
    }
}
