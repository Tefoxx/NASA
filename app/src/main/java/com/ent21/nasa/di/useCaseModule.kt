package com.ent21.nasa.di

import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData
import org.koin.dsl.module

val useCaseModule = module {
    single { GetFeedPagingUseCaseAsLiveData(get()) }
}