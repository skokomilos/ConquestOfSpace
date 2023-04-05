package com.space.conquestofspace.data.remote.dto.astronaut

data class Landing(
    val destination: String,
    val id: Int,
    val launch: Launch,
    val mission_end: String,
    val spacecraft: Spacecraft,
    val url: String
)
