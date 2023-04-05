package com.space.conquestofspace.data.remote.dto.astronaut

data class SpacecraftConfig(
    val agency: Agency,
    val id: Int,
    val image_url: String,
    val in_use: Boolean,
    val name: String,
    val url: String
)
