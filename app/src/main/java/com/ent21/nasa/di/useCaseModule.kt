package com.ent21.nasa.di

import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData
import com.ent21.nasa.usecase.UpdateFeedUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetFeedPagingUseCaseAsLiveData(get()) }
    single { UpdateFeedUseCase(get(), get()) }
}