package com.example.restaurantsapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.restaurantsapp.data.remote.api.ApiConstants
import com.example.restaurantsapp.data.remote.api.Restaurant
import com.example.restaurantsapp.data.remote.api.RestaurantRemoteSource

class RestaurantPagingSource(
    private val restaurantRemoteSource: RestaurantRemoteSource
) : PagingSource<Int, Restaurant>() {

    override fun getRefreshKey(state: PagingState<Int, Restaurant>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        return try {
            val currentPage = params.key ?: ApiConstants.RADIUS
            val restaurants = restaurantRemoteSource.getRestaurants(radius = currentPage)
            LoadResult.Page(
                data = restaurants.body()?.results!!,
                prevKey = if (currentPage == ApiConstants.RADIUS) null else currentPage - ApiConstants.RADIUS,
                nextKey = if (restaurants.body()?.results.isNullOrEmpty() || currentPage == ApiConstants.MAX_RADIUS) {
                    null
                } else {
                    currentPage + ApiConstants.RADIUS
                }
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}