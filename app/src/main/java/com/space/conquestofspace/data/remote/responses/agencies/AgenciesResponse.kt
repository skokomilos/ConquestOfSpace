package com.space.conquestofspace.data.remote.responses.agencies

data class AgenciesResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val agencies: List<Agency>
)
