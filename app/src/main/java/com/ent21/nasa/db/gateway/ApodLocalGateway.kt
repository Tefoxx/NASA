package com.ent21.nasa.db.gateway

import androidx.paging.PagingSource
import com.ent21.nasa.api.model.Apod
import com.ent21.nasa.db.entity.ApodEntity
import java.util.*

interface ApodLocalGateway {
    suspend fun insertAll(apods: List<Apod>)
    fun getSource(): PagingSource<Int, ApodEntity>
    suspend fun clearAndInsertAll(apods: List<Apod>)
    suspend fun getLastItem(): ApodEntity?
    suspend fun clearAll()
}