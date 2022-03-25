package com.space.conquestofspace.data.remote.dto

import com.space.conquestofspace.domain.model.Location

data class LocationDTO(
    val id: Int,
    val url: String,
    val name: String,
    val country_code: String,
    val map_image: String
){
    fun toLocation(): Location{
        return Location(
            id = id,
            name = name
        )
    }
}