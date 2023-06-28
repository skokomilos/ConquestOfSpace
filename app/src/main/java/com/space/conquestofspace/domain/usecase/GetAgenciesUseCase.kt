package com.space.conquestofspace.domain.usecase

import com.space.conquestofspace.data.remote.responses.agencies.Agency
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author berka on 6/21/23
 */
class GetAgenciesUseCase(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<List<Agency>>> = repository.getAgencies()
}
