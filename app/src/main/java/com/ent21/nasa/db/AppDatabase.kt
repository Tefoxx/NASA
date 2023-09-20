package com.ent21.nasa.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ent21.nasa.db.dao.ApodDao
import com.ent21.nasa.db.entity.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}