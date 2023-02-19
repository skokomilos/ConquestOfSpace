package com.space.conquestofspace.data.remote

import com.space.conquestofspace.data.remote.dto.LaunchesResponse
import com.space.conquestofspace.data.remote.dto.iss.SpaceStationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSpaceDevApi {

    @GET("/2.2.0/launch/")
    suspend fun getLaunches(
        @Query("window_start__gte") string: String
    ): LaunchesResponse

    @GET("/2.2.0/expedition/150/")
    suspend fun getLatestISSExpedition(): SpaceStationResponse
}