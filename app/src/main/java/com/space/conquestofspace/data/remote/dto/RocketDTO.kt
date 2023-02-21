package com.space.conquestofspace.data.remote.dto

import com.space.conquestofspace.domain.model.Rocket

data class RocketDTO(
    val configuration: RocketConfigurationDTO,
    val id: Int
) {
    fun toRocket(): Rocket {
        return Rocket(
            configuration = configuration,
            id = id
        )
    }
}
