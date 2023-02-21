package com.space.conquestofspace.data.remote.dto.iss

data class Spacestation(
    val description: String,
    val founded: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val orbit: String,
    val owners: List<Owner>,
    val status: Status,
    val url: String
)
