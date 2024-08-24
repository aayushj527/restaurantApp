package com.example.restaurantsapp.di

import com.example.restaurantsapp.data.remote.api.RestaurantService
import com.example.restaurantsapp.utils.AuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CONNECTION_TIMEOUT = 30L
const val BASE_URL = "https://api.foursquare.com"

val networkModule = module {
    single {
        Retrofit
            .Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(AuthInterceptor())
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .build()
    }

    factory { get<Retrofit>().create(RestaurantService::class.java) }
}