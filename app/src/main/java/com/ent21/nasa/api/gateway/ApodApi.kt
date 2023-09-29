package com.ent21.nasa.api.gateway

import com.ent21.nasa.api.ApiWebService
import com.ent21.nasa.api.model.Apod
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ApodApi(private val service: ApiWebService) : ApodRemoteGateway {
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)

    override suspend fun getApodsByDate(startDate: Date, endDate: Date): List<Apod> =
        with(simpleDateFormat) {
            service.getApodsByDate(format(startDate), format(endDate))
        }

    override suspend fun getApodsRandom(count: Int): List<Apod> = service.getApodsRandom(count)
}