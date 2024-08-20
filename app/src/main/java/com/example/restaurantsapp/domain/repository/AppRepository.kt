package com.example.restaurantsapp.domain.repository

import com.example.restaurantsapp.data.local.entity.UserEntity

interface AppRepository {
    suspend fun getUser(mobileNumber : String): UserEntity?

    suspend fun register(userEntity: UserEntity)
}