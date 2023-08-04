package com.space.conquestofspace.domain.usecase.agency

import com.space.conquestofspace.data.remote.responses.agencies.Agency
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author berka on 7/28/23
 */
class GetAgencyUseCase(
    private val repository: MainRepository
) {

    operator fun invoke(id: Int): Flow<Resource<Agency>> = repository.getAgencyById(id)
}
