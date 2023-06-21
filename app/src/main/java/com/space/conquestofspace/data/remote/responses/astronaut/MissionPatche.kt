package com.space.conquestofspace.data.remote.responses.astronaut

data class MissionPatche(
    val agency: com.space.conquestofspace.data.remote.responses.astronaut.Agency,
    val id: Int,
    val image_url: String,
    val name: String,
    val priority: Int
)
