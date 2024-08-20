package com.example.restaurantsapp.data.repository

import com.example.restaurantsapp.data.local.AppDao
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.domain.repository.AppRepository

class AppRepositoryImpl(
    private val dao: AppDao
): AppRepository {
    override suspend fun getUser(mobileNumber: String): UserEntity? {
        return dao.getUser(mobileNumber)
    }

    override suspend fun register(userEntity: UserEntity) {
        dao.register(userEntity)
    }
}