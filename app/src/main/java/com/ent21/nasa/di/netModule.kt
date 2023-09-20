package com.ent21.nasa.di

import com.ent21.nasa.api.ApiWebService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ent21.nasa.BuildConfig.API_URL
import com.ent21.nasa.api.gateway.ApodApi
import com.ent21.nasa.api.gateway.ApodRemoteGateway
import com.google.gson.GsonBuilder

val netModule = module {
    single {
        val gsonDateConverter = GsonBuilder().setDateFormat("YYYY-MM-DD").create()
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonDateConverter))
            .build()
    }
    single { get<Retrofit>().create(ApiWebService::class.java) }

    single<ApodRemoteGateway> { ApodApi(get()) }
}