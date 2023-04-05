package com.space.conquestofspace.data.remote.dto.astronaut

data class PadX(
    val agency_id: Int,
    val id: Int,
    val info_url: String,
    val latitude: String,
    val longitude: String,
    val map_image: String,
    val map_url: String,
    val name: String,
    val orbital_launch_attempt_count: Int,
    val total_launch_count: Int,
    val url: String,
    val wiki_url: String
)
