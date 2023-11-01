package com.space.conquestofspace.data.remote.responses.iss

data class Crew(
    val astronaut: com.space.conquestofspace.data.remote.responses.iss.Astronaut,
    val id: Int,
    val role: com.space.conquestofspace.data.remote.responses.iss.Role
)
