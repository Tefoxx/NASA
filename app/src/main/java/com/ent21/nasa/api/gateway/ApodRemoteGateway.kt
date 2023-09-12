package com.ent21.nasa.api.gateway

import com.ent21.nasa.api.model.Apod
import java.util.*

interface ApodRemoteGateway {
    suspend fun getApodsByDate(startDate: Date, endDate: Date): List<Apod>
    suspend fun getApodsRandom(count: Int): List<Apod>
}