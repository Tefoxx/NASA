package com.ent21.nasa.di

import com.ent21.nasa.BuildConfig.API_URL
import com.ent21.nasa.api.ApiWebService
import com.ent21.nasa.api.gateway.ApodApi
import com.ent21.nasa.api.gateway.ApodRemoteGateway
import com.ent21.nasa.ui.feed.FeedRemoteMediator
import com.ent21.nasa.utils.ImageManager
import com.google.gson.GsonBuilder
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netModule = module {
    single {
        val gsonDateConverter = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonDateConverter))
            .build()
    }
    single { get<Retrofit>().create(ApiWebService::class.java) }

    single<ApodRemoteGateway> { ApodApi(get()) }

    factory { FeedRemoteMediator(get(), get()) }

    single { ImageManager(get(), get(named(APP_SCOPE))) }
}