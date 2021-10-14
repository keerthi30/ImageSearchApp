package com.example.cvssample

import android.app.Application
import com.example.cvssample.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FlickrApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@FlickrApplication)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule, dataBaseModule))
        }
    }
}