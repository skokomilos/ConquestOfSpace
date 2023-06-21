package com.space.conquestofspace.presentation.iss

import com.space.conquestofspace.data.remote.responses.iss.SpaceStationResponse

data class IssState(
    val iss: SpaceStationResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
