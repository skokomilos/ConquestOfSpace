package com.space.conquestofspace.data.remote.dto

data class LaunchesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<LaunchDTO>
)