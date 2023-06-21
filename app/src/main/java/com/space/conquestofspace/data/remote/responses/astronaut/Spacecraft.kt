package com.space.conquestofspace.data.remote.responses.astronaut

data class Spacecraft(
    val description: String,
    val id: Int,
    val name: String,
    val serial_number: String,
    val spacecraft_config: com.space.conquestofspace.data.remote.responses.astronaut.SpacecraftConfig,
    val url: String
)
