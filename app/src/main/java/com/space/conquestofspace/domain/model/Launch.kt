package com.space.conquestofspace.domain.model

data class Launch(
    val name: String?,
    val status: Status,
    val window_start: String,
    val rocket: Rocket,
    // val pad: Pad,
    val image: String? = "",
    val webcast_live: Boolean
)
