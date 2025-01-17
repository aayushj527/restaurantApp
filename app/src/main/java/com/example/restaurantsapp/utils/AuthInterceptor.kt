package com.example.restaurantsapp.utils

import com.example.restaurantsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Authorization", BuildConfig.API_KEY).build()
        return chain.proceed(request)
    }
}