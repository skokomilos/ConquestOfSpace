package com.space.conquestofspace.presentation.international_space_station_screen

import com.space.conquestofspace.data.remote.dto.iss.SpaceStationResponse

data class IssState(
    val iss: SpaceStationResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
