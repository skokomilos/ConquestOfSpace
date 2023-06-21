package com.space.conquestofspace.data.remote.dto.launches

import com.space.conquestofspace.domain.model.Status

data class StatusDTO(
    val id: Int,
    val abbrev: String
) {
    fun toStatus(): Status {
        return Status(
            id = id,
            abbrev = abbrev
        )
    }
}
