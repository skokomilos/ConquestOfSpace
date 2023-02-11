package com.space.conquestofspace.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LaunchesResponse(
    @SerializedName("results")
    val launches: List<LaunchDTO>
)