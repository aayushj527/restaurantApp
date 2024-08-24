package com.example.restaurantsapp.data.remote.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRemoteSource(private val restaurantService: RestaurantService) {

    suspend fun getRestaurants(radius: Int) = withContext(Dispatchers.IO) {
        restaurantService.getRestaurants(
            query = ApiConstants.QUERY,
            radius = radius,
            limit = ApiConstants.LIMIT
        )
    }
}