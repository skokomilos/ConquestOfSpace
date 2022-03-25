package com.space.conquestofspace.data.local

import androidx.room.Database
import com.space.conquestofspace.data.local.dao.LaunchDao
import com.space.conquestofspace.data.local.entity.LaunchEntity

@Database(
    entities = [LaunchEntity::class],
    version = 1
)
abstract class SpaceDB {

    abstract val dao: LaunchDao
}