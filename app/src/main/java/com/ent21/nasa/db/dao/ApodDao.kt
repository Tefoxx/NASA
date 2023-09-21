package com.ent21.nasa.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ent21.nasa.db.entity.ApodEntity

@Dao
interface ApodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(apods: List<ApodEntity>)

    @Query("select * from ${ApodEntity.NAME} order by num")
    fun getPagingSource(): PagingSource<Int, ApodEntity>

    @Query("select max(num) from ${ApodEntity.NAME}")
    suspend fun getLastItem(): Int?

    @Query("delete from ${ApodEntity.NAME}")
    suspend fun clearAll()
}