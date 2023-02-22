package com.space.conquestofspace.presentation.launches

import com.space.conquestofspace.domain.model.Launch

data class LaunchesState(
    val launches: List<Launch> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
