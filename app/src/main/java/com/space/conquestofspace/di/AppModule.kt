package com.space.conquestofspace.di

import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.data.repository.LaunchRepositoryImpl
import com.space.conquestofspace.domain.repository.MainRepository
import com.space.conquestofspace.domain.use_case.get_iss.GetIssUseCase
import com.space.conquestofspace.domain.use_case.get_launches.GetLaunchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLaunchRepository(
        api: TheSpaceDevApi,
        launchDao: LaunchDao
    ): MainRepository{
        return LaunchRepositoryImpl(api, launchDao)
    }

    @Provides
    @Singleton
    fun provideLaunchesUseCase(repository: MainRepository): GetLaunchesUseCase {
        return GetLaunchesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIssUseCase(repository: MainRepository): GetIssUseCase {
        return GetIssUseCase(repository)
    }
}