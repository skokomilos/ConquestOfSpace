package com.space.conquestofspace.presentation.iss

import com.space.conquestofspace.data.remote.dto.astronaut.AstronautResponse

data class AstronautState(
    val astronaut: AstronautResponse? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
