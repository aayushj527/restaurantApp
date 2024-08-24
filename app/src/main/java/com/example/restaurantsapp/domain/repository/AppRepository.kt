package com.example.restaurantsapp.domain.repository

import androidx.paging.PagingData
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.data.remote.api.Restaurant
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getUser(mobileNumber : String): UserEntity?

    suspend fun register(userEntity: UserEntity)

    suspend fun getRestaurants(): Flow<PagingData<Restaurant>>

    suspend fun rejectRestaurant(fsqId: String)

    suspend fun getRejectRestaurants(): ArrayList<String>?
}