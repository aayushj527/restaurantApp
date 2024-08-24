package com.example.restaurantsapp

import android.app.Application
import com.example.restaurantsapp.di.databaseModule
import com.example.restaurantsapp.di.networkModule
import com.example.restaurantsapp.di.remoteSourceModule
import com.example.restaurantsapp.di.repositoryModule
import com.example.restaurantsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(databaseModule, remoteSourceModule, viewModelModule, repositoryModule, networkModule)
        }
    }
}