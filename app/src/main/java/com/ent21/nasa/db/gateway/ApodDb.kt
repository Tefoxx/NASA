package com.ent21.nasa.db.gateway

import androidx.room.withTransaction
import com.ent21.nasa.api.model.Apod
import com.ent21.nasa.api.model.MediaType
import com.ent21.nasa.db.AppDatabase
import com.ent21.nasa.db.dao.ApodDao
import com.ent21.nasa.db.entity.ApodEntity

private const val DEFAULT_NUM = 0

class ApodDb(private val database: AppDatabase, private val dao: ApodDao) : ApodLocalGateway {
    override suspend fun insertAll(apods: List<Apod>) = database.withTransaction {
        val lastNum = (getLastItemNum() ?: DEFAULT_NUM) + 1
        dao.insertAll(
            apods.mapIndexed { index, apod ->
                apod.toDb(lastNum + index)
            }
        )
    }

    override fun getSource() = dao.getPagingSource()

    override suspend fun clearAndInsertAll(apods: List<Apod>) = database.withTransaction {
        clearAll()
        insertAll(apods)
    }

    override suspend fun getLastItemNum() = dao.getLastItem()

    override suspend fun clearAll() = dao.clearAll()
}

fun Apod.toDb(num: Int) = ApodEntity(
    num = num,
    date = date,
    explanation = explanation,
    hdUrl = hdUrl,
    url = url,
    thumbnailUrl = thumbnailUrl,
    mediaType = MediaType.getByTitle(mediaType),
    title = title
)