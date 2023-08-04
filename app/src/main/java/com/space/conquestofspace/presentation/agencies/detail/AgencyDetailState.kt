package com.space.conquestofspace.presentation.agencies.detail

import com.space.conquestofspace.data.remote.responses.agencies.Agency

/**
 *
 * @author berka on 6/27/23
 */
data class AgencyDetailState(
    val agency: Agency? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
