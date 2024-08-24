package com.example.restaurantsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.restaurantsapp.data.local.AppDao
import com.example.restaurantsapp.data.local.entity.ReviewEntity
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.data.remote.api.ApiConstants
import com.example.restaurantsapp.data.remote.api.Restaurant
import com.example.restaurantsapp.data.remote.api.RestaurantRemoteSource
import com.example.restaurantsapp.data.remote.paging.RestaurantPagingSource
import com.example.restaurantsapp.domain.repository.AppRepository
import com.example.restaurantsapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val dao: AppDao,
    private val restaurantRemoteSource: RestaurantRemoteSource
) : AppRepository {

    override suspend fun getUser(mobileNumber: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            dao.getUser(mobileNumber)
        }
    }

    override suspend fun register(userEntity: UserEntity) {
        withContext(Dispatchers.IO) {
            dao.register(userEntity)
        }
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
        withContext(Dispatchers.IO) {
            val rejectedRestaurants = dao
                .getUser(Constants.currentlyLoggedInUserNumber)
                ?.rejectedRestaurants
                ?.apply {
                    if (!contains(fsqId)) {
                        add(fsqId)
                    }
                } ?: arrayListOf(fsqId)

            dao.updateRejectedRestaurants(
                Constants.currentlyLoggedInUserNumber,
                rejectedRestaurants
            )
        }
    }

    override suspend fun getRejectRestaurants(): ArrayList<String>? {
        return withContext(Dispatchers.IO) {
            dao.getUser(Constants.currentlyLoggedInUserNumber)?.rejectedRestaurants
        }
    }

    override fun getReviews(): Flow<List<ReviewEntity>> {
        return dao.getReviews()
    }

    override suspend fun updateReviews(fsqId: String, reviews: HashMap<String, String>) {
        withContext(Dispatchers.IO) {
            dao.updateReviews(ReviewEntity(fsqId, reviews))
        }
    }
}