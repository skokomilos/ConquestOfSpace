package com.space.conquestofspace.data.remote.responses.astronaut

data class Mission(
    val description: String,
    val id: Int,
    val launch_designator: String,
    val name: String,
    val orbit: com.space.conquestofspace.data.remote.responses.astronaut.Orbit,
    val type: String
)
