package com.example.restaurantsapp.di

import androidx.room.Room
import com.example.restaurantsapp.data.local.AppDao
import com.example.restaurantsapp.data.local.Database
import com.example.restaurantsapp.data.repository.AppRepositoryImpl
import com.example.restaurantsapp.domain.repository.AppRepository
import com.example.restaurantsapp.presentation.authScreen.AuthScreenVM
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val DATABASE_NAME = "app_database"

val appModule = module {
    single { Room.databaseBuilder(androidContext(), Database::class.java, DATABASE_NAME).build() }
    single<AppDao> {
        val database = get<Database>()
        database.dao()
    }
    single<AppRepository> { AppRepositoryImpl(get()) }
    viewModel { AuthScreenVM(get()) }
}