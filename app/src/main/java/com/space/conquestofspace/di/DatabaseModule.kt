package com.space.conquestofspace.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.space.conquestofspace.data.local.SpaceDB
import com.space.conquestofspace.data.local.converters.Converters
import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.local.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): SpaceDB {
        return Room.databaseBuilder(
            app,
            SpaceDB::class.java,
            "space_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideLaunchDao(db: SpaceDB): LaunchDao {
        return db.launchDao()
    }
}
