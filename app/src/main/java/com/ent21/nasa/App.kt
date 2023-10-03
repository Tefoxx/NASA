package com.ent21.nasa

import android.app.Application
import com.ent21.nasa.di.coroutineModule
import com.ent21.nasa.di.dbModule
import com.ent21.nasa.di.netModule
import com.ent21.nasa.di.uiModule
import com.ent21.nasa.di.useCaseModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
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
                uiModule,
                dbModule,
                useCaseModule,
                coroutineModule,
            )
        }
    }
}