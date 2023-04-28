package com.space.conquestofspace.data.remote.dto.astronaut

data class AstronautResponse(
    val age: Int,
    val agency: Agency,
    val bio: String,
    val date_of_birth: String,
    val date_of_death: String,
    val first_flight: String,
    val flights: List<Flight>?,
    val flights_count: Int,
    val id: Int,
    val in_space: Boolean,
    val instagram: String,
    val landings: List<Landing>?,
    val landings_count: Int,
    val last_flight: String,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val status: StatusXXX,
    val twitter: String,
    val type: TypeX,
    val url: String,
    val wiki: String
)
