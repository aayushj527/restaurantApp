package com.example.restaurantsapp.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {

    @GET("v3/places/search")
    suspend fun getRestaurants(
        @Query("query") query: String,
        @Query("radius") radius: Int,
        @Query("limit") limit: Int
    ): Response<RestaurantsResponse>
}