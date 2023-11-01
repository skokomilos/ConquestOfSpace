package com.space.conquestofspace.domain.usecase

import com.space.conquestofspace.data.remote.responses.astronaut.AstronautResponse
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetAstronautUseCase constructor(
    private val repository: MainRepository
) {

    operator fun invoke(id: Int): Flow<Resource<AstronautResponse>> {
        return repository.getAstronautById(id)
    }
}
