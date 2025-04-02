package com.example.metgallery

import android.app.Application
import com.example.metgallery.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(AppModule)
        }
    }
}