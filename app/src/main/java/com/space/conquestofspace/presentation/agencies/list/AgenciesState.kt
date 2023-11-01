package com.space.conquestofspace.presentation.agencies.list

import com.space.conquestofspace.data.remote.responses.agencies.Agency

/**
 *
 * @author berka on 6/27/23
 */
data class AgenciesState(
    val agencies: List<Agency> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
