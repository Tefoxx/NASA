package com.ent21.nasa.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.ent21.nasa.BuildConfig.API_KEY
import androidx.annotation.IntRange
import com.ent21.nasa.api.model.Apod

interface ApiWebService {
    @GET("planetary/apod")
    suspend fun getApodsByDate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = API_KEY,
    ) : List<Apod>

    @GET("planetary/apod")
    suspend fun getApodsRandom(
        @Query("count") @IntRange(0, 100) count: Int,
        @Query("thumbs") thumbs: Boolean = true,
        @Query("api_key") apiKey: String = API_KEY,
    ) : List<Apod>
}