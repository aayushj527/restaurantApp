package com.example.restaurantsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.restaurantsapp.data.local.AppDao
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.data.remote.api.ApiConstants
import com.example.restaurantsapp.data.remote.api.Restaurant
import com.example.restaurantsapp.data.remote.api.RestaurantRemoteSource
import com.example.restaurantsapp.data.remote.paging.RestaurantPagingSource
import com.example.restaurantsapp.domain.repository.AppRepository
import com.example.restaurantsapp.utils.Constants
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val dao: AppDao,
    private val restaurantRemoteSource: RestaurantRemoteSource
): AppRepository {

    override suspend fun getUser(mobileNumber: String): UserEntity? {
        return dao.getUser(mobileNumber)
    }

    override suspend fun register(userEntity: UserEntity) {
        dao.register(userEntity)
    }

    override suspend fun getRestaurants(): Flow<PagingData<Restaurant>> {
        return Pager(
            config = PagingConfig(pageSize = ApiConstants.LIMIT, prefetchDistance = 2),
            pagingSourceFactory = {
                RestaurantPagingSource(restaurantRemoteSource)
            }
        ).flow
    }

    override suspend fun rejectRestaurant(fsqId: String) {
        val rejectedRestaurants = dao
            .getUser(Constants.currentlyLoggedInUser)
            ?.rejectedRestaurants
            ?.apply {
                if (!contains(fsqId)) {
                    add(fsqId)
                }
            } ?: arrayListOf(fsqId)

        dao.updateRejectedRestaurants(Constants.currentlyLoggedInUser, rejectedRestaurants)
    }

    override suspend fun getRejectRestaurants(): ArrayList<String>? {
        return dao.getUser(Constants.currentlyLoggedInUser)?.rejectedRestaurants
    }
}