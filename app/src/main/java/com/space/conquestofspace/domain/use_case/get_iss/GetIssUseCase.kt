package com.space.conquestofspace.domain.use_case.get_iss

import com.space.conquestofspace.data.remote.dto.iss.SpaceStationResponse
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetIssUseCase constructor(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<SpaceStationResponse>> {
        return repository.getIss()
    }
}
