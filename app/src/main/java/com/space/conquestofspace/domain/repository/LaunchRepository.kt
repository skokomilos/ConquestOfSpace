package com.space.conquestofspace.domain.repository

import com.space.conquestofspace.domain.model.Launch
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {

    fun getLaunches(): Flow<Resource<List<Launch>>>
}