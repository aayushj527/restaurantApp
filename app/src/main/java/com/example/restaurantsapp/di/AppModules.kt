package com.example.restaurantsapp.di

import androidx.room.Room
import com.example.restaurantsapp.data.local.Database
import com.example.restaurantsapp.data.remote.api.RestaurantRemoteSource
import com.example.restaurantsapp.data.repository.AppRepositoryImpl
import com.example.restaurantsapp.domain.repository.AppRepository
import com.example.restaurantsapp.presentation.authScreen.AuthScreenVM
import com.example.restaurantsapp.presentation.homeScreen.HomeScreenVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val DATABASE_NAME = "app_database"

val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), Database::class.java, DATABASE_NAME).build() }
    factory { get<Database>().dao() }
}

val remoteSourceModule = module {
    factory { RestaurantRemoteSource(get()) }
}

val repositoryModule = module {
    single<AppRepository> { AppRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { AuthScreenVM(get()) }
    viewModel { HomeScreenVM(get()) }
}