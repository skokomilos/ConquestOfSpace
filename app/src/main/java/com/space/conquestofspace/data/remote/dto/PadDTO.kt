package com.space.conquestofspace.data.remote.dto

data class PadDTO(
    val id: Int,
    val url: String,
    val agency_id: Any,
    val name: String,
    val info_url: Any,
    val wiki_url: String,
    val location: LocationDTO,
    val map_image: String,
    val map_url: String,
)