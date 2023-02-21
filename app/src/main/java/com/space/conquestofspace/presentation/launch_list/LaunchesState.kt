package com.space.conquestofspace.presentation.launch_list

import com.space.conquestofspace.domain.model.Launch

data class LaunchesState(
    val launches: List<Launch> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
