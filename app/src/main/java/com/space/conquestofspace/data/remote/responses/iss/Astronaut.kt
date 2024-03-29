package com.space.conquestofspace.data.remote.responses.iss

data class Astronaut(
    val agency: Agency,
    val id: Int,
    val name: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val status: Status,
    val url: String
)
