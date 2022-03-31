package com.space.conquestofspace.data.local

import androidx.room.Database
import androidx.room.TypeConverters
import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.local.entity.LaunchEntity

@Database(
    entities = [LaunchEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SpaceDB {

    abstract val dao: LaunchDao
}