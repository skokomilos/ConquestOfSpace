package com.space.conquestofspace.data.local.dao

import android.util.Log
import androidx.room.*
import com.space.conquestofspace.data.local.entity.LaunchEntity
import com.space.conquestofspace.data.remote.dto.iss.SpaceStationResponse

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("SELECT * FROM launchentity")
    suspend fun getLaunches(): List<LaunchEntity>

    @Query("DELETE FROM launchentity")
    suspend fun deleteLaunches()
}