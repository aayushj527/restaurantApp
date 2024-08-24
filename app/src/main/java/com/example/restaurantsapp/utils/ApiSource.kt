package com.example.restaurantsapp.utils

sealed class ApiSource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null): ApiSource<T>(data)
    class Error<T>(message: String, data: T? = null): ApiSource<T>(data, message)
    class Loading<T>(val isLoading: Boolean): ApiSource<T>()
}