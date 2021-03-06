package com.space.conquestofspace.di

import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.data.repository.LaunchRepositoryImpl
import com.space.conquestofspace.domain.repository.LaunchRepository
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
    ): LaunchRepository{
        return LaunchRepositoryImpl(api, launchDao)
    }
}