package com.ent21.nasa.db.gateway

import com.ent21.nasa.api.model.Apod
import java.util.*

interface ApodLocalGateway {
    fun getApodsByDate(startDate: Date, endDate: Date): List<Apod>
    fun getApodsRandom(count: Int): List<Apod>
    fun saveApods(list: List<Apod>)
    fun deleteAll()
}