package com.space.conquestofspace.data.remote.dto

import com.space.conquestofspace.domain.model.RocketConfiguration

data class RocketConfigurationDTO(
    val id: Int,
    val url: String,
    val name: String,
    val family: String,
    val full_name: String
) {
    fun toRocketConfiguration(): RocketConfiguration {
        return RocketConfiguration(
            id = id,
            url = url,
            name = name,
            family = family,
            full_name = full_name
        )
    }
}
