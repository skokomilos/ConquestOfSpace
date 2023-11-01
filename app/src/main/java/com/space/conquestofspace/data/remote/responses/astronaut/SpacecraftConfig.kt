package com.space.conquestofspace.data.remote.responses.astronaut

data class SpacecraftConfig(
    val agency: com.space.conquestofspace.data.remote.responses.astronaut.Agency,
    val id: Int,
    val image_url: String,
    val in_use: Boolean,
    val name: String,
    val url: String
)
