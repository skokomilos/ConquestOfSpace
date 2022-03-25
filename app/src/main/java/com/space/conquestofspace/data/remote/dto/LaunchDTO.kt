package com.space.conquestofspace.data.remote.dto

import com.space.conquestofspace.domain.model.Launch

/*
    Data Transfer Object
 */

data class LaunchDTO(
    val id: String,
    val url: String,
    val slug: String,
    //1. need
    val name: String,
    //7. need - abbrev
    val status: StatusDTO,
    val last_updated: String,
    val net: String,
    val window_end: String,
    //4/5/6. need
    val window_start: String,
    //2. need -name
    val rocket: RocketDTO,
    //3. need -location - name
    val pad: PadDTO,
    //8. need
    val image: String,
    val webcast_live: Boolean
){
    //mapper fun
    fun toLaunch(): Launch {
        return Launch(
            name = name,
            status = status.toStatus(),
            window_start = window_start,
            rocket = rocket.toRocket(),
            pad = pad.toPad(),
            image = image,
            webcast_live = webcast_live
        )
    }
}