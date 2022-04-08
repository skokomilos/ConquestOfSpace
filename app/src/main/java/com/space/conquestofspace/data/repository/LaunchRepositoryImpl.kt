package com.space.conquestofspace.data.repository

import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.domain.model.Launch
import com.space.conquestofspace.domain.repository.LaunchRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LaunchRepositoryImpl(
    private val api: TheSpaceDevApi,
    private val dao: LaunchDao
): LaunchRepository {

    override fun getLaunches(currentTime: String): Flow<Resource<List<Launch>>> = flow{
        emit(Resource.Loading())

        val launches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Loading(data = launches))

        try {
            val remoteLaunches = api.getLaunches(currentTime);
            dao.deleteLaunches()
            dao.insertLaunches(remoteLaunches.launches.map { it.toLaunchEntity() })
        } catch (e: HttpException){
            emit(Resource.Error(
                message = e.localizedMessage ?: "Unexpected error",
                data = launches))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Check out network",
                data = launches))
        }

        val newLaunches = dao.getLaunches().map { it.toLaunch() }
        emit(Resource.Success(newLaunches))
    }
}