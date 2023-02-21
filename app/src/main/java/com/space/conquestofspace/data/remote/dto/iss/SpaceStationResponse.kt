package com.space.conquestofspace.data.remote.dto.iss

data class SpaceStationResponse(
    val crew: List<Crew>,
    val end: Any,
    val id: Int,
    val mission_patches: List<MissionPatche>,
    val name: String,
    val spacestation: Spacestation,
    val start: String,
    val url: String
)
