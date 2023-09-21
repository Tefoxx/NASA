package com.ent21.nasa.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ent21.nasa.db.dao.ApodDao
import com.ent21.nasa.db.entity.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao

    companion object {
        const val NAME = "app_database"
    }
}