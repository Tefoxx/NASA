package com.ent21.nasa.di

import androidx.room.Room
import com.ent21.nasa.db.AppDatabase
import com.ent21.nasa.db.gateway.ApodDb
import com.ent21.nasa.db.gateway.ApodLocalGateway
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single <AppDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            AppDatabase.NAME
        ).build()
    }

    single { get<AppDatabase>().apodDao() }
    single<ApodLocalGateway> { ApodDb(get(), get()) }
}