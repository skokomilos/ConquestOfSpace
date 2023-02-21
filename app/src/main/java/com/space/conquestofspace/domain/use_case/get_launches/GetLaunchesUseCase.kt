package com.space.conquestofspace.domain.use_case.get_launches

import com.space.conquestofspace.domain.model.Launch
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.core.util.HelperMethods.parseCurrentTimeToSimpleDateFormat
import com.space.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetLaunchesUseCase constructor(
    private val repository: MainRepository
) {

    operator fun invoke(): Flow<Resource<List<Launch>>> {
        return repository.getLaunches(
            parseCurrentTimeToSimpleDateFormat())
    }
}