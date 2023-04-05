package com.space.conquestofspace.data.remote.dto.astronaut

data class Spacecraft(
    val description: String,
    val id: Int,
    val name: String,
    val serial_number: String,
    val spacecraft_config: SpacecraftConfig,
    val url: String
)
