package com.space.conquestofspace.data.repository

import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.domain.model.Launch
import com.space.conquestofspace.domain.repository.LaunchRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchRepositoryImpl(
    private val api: TheSpaceDevApi,
    private val dao: LaunchDao
): LaunchRepository {

    override fun getLaunches(): Flow<Resource<List<Launch>>> = flow{
        emit(Resource.Loading())

        //val launches = dao.getLaunches()
    }
}