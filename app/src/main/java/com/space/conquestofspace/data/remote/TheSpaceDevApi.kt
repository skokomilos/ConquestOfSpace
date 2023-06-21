package com.space.conquestofspace.data.remote

import com.space.conquestofspace.data.remote.dto.launches.LaunchesResponse
import com.space.conquestofspace.data.remote.responses.agencies.AgenciesResponse
import com.space.conquestofspace.data.remote.responses.astronaut.AstronautResponse
import com.space.conquestofspace.data.remote.responses.iss.SpaceStationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheSpaceDevApi {

    @GET("/2.2.0/launch/")
    suspend fun getLaunches(
        @Query("window_start__gte") string: String
    ): LaunchesResponse

    @GET("/2.2.0/expedition/150/")
    suspend fun getLatestISSExpedition(): SpaceStationResponse

    @GET("/2.2.0/astronaut/{id}")
    suspend fun getAstronautById(@Path("id") id: Int): AstronautResponse

    @GET("/2.2.0/agencies/")
    suspend fun getAgencies(): AgenciesResponse
}
