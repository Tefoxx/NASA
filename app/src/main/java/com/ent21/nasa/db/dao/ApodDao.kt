package com.ent21.nasa.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.ent21.nasa.db.entity.ApodEntity

@Dao
interface ApodDao {
    @Query("select * FROM apodEntity")
    fun getAll(): List<ApodEntity>
}