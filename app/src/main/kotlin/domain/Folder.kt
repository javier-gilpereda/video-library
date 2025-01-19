package com.gilpereda.videomanager.domain

interface Folder {
    val children: List<Folder>
    val id: String
    val name: String
}
