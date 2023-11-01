package com.space.conquestofspace.data.remote.responses.astronaut

data class Landing(
    val destination: String,
    val id: Int,
    val launch: com.space.conquestofspace.data.remote.responses.astronaut.Launch,
    val mission_end: String,
    val spacecraft: com.space.conquestofspace.data.remote.responses.astronaut.Spacecraft,
    val url: String
)
