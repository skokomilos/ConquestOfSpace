package com.space.conquestofspace.domain.model

import com.space.conquestofspace.data.remote.dto.RocketConfigurationDTO

data class Rocket(
    val configuration: RocketConfigurationDTO,
    val id: Int
)
