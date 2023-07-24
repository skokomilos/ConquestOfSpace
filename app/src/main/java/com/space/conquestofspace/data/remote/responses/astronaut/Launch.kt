package com.space.conquestofspace.data.remote.responses.astronaut

data class Launch(
    val agency_launch_attempt_count: Int,
    val agency_launch_attempt_count_year: Int,
    val failreason: String,
    val hashtag: String,
    val holdreason: String,
    val id: String,
    val image: String,
    val infographic: String,
    val last_updated: String,
    val location_launch_attempt_count: Int,
    val location_launch_attempt_count_year: Int,
    val mission: com.space.conquestofspace.data.remote.responses.astronaut.MissionX,
    val name: String,
    val net: String,
    val orbital_launch_attempt_count: Int,
    val orbital_launch_attempt_count_year: Int,
    val pad: com.space.conquestofspace.data.remote.responses.astronaut.PadX,
    val pad_launch_attempt_count: Int,
    val pad_launch_attempt_count_year: Int,
    val probability: Int,
    val program: List<com.space.conquestofspace.data.remote.responses.astronaut.Program>,
    val rocket: com.space.conquestofspace.data.remote.responses.astronaut.RocketX,
    val slug: String,
    val url: String,
    val webcast_live: Boolean,
    val window_end: String,
    val window_start: String
)