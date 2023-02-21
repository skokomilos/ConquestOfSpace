package com.space.conquestofspace.data.repository

import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.data.remote.dto.iss.SpaceStationResponse
import com.space.conquestofspace.domain.model.Launch
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.Resource
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class LaunchRepositoryImpl(
    private val api: TheSpaceDevApi,
    private val dao: LaunchDao
) : MainRepository {

    override fun getLaunches(currentTime: String): Flow<Resource<List<Launch>>> = flow {
        emit(Resource.Loading())

        val launches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Loading(data = launches))

        try {
            val remoteLaunches = api.getLaunches(currentTime)
            dao.deleteLaunches()
            dao.insertLaunches(remoteLaunches.launches.map { it.toLaunchEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "Unexpected error",
                    data = launches
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Check out network",
                    data = launches
                )
            )
        }

        val newLaunches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Success(newLaunches))
    }

    override fun getIss(): Flow<Resource<SpaceStationResponse>> = flow {
        emit(Resource.Loading())

        try {
            val iss = api.getLatestISSExpedition()
            emit(Resource.Success(iss))
        } catch (e: java.lang.Exception) {
            emit(
                Resource.Error(
                    message = "General error"
                )
            )
        }
    }
}
