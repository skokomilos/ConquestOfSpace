package com.space.conquestofspace.presentation.iss.astronautDetail

import com.space.conquestofspace.data.remote.responses.astronaut.AstronautResponse

data class AstronautState(
    val astronaut: AstronautResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
