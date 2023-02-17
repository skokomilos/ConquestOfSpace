package com.space.conquestofspace.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.space.conquestofspace.domain.model.Launch
import com.space.conquestofspace.domain.model.Rocket
import com.space.conquestofspace.domain.model.Status

@Entity
data class LaunchEntity(
    val name: String,
    val status: Status,
    val window_start: String,
    val rocket: Rocket,
  //  val pad: Pad,
    val image: String,
    val webcast_live: Boolean,
    @PrimaryKey val id: Int? = null
){
    fun toLaunch(): Launch{
        return Launch(
            name = name,
            status = status,
            window_start = window_start,
            rocket = rocket,
            //pad = pad,
            image = image,
            webcast_live = webcast_live
        )
    }
}
