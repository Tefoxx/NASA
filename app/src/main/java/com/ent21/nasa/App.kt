package com.ent21.nasa

import android.app.Application
import com.ent21.nasa.di.dbModule
import com.ent21.nasa.di.netModule
import com.ent21.nasa.di.useCaseModule
import com.ent21.nasa.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                netModule,
                vmModule,
                dbModule,
                useCaseModule,
            )
        }
    }
}