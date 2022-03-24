package com.space.conquestofspace.data.remote.dto

data class LaunchDTO(
    val id: String,
    val url: String,
    val slug: String,
    val name: String,
    val status: StatusDTO,
    val last_updated: String,
    val net: String,
    val window_end: String,
    val window_start: String,
    val launch_service_provider: LaunchServiceProviderDTO,
    val rocket: RocketDTO,
    val mission: MissionDTO,
    val pad: PadDTO,
    val image: String,
    val webcast_live: Boolean,
)