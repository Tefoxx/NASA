package com.ent21.nasa.db.gateway

import com.ent21.nasa.api.model.Apod
import com.ent21.nasa.db.dao.ApodDao
import java.util.*

class ApodDb(private val dao: ApodDao) : ApodLocalGateway {
    override fun getApodsByDate(startDate: Date, endDate: Date): List<Apod> {
        return listOf<Apod>()
    }

    override fun getApodsRandom(count: Int): List<Apod> {
        return listOf<Apod>()
    }

    override fun saveApods(list: List<Apod>) {

    }

    override fun deleteAll() {

    }
}