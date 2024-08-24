package com.example.restaurantsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.utils.Constants

@Dao
interface AppDao {
    @Query("SELECT * FROM userentity WHERE mobileNumber = :mobileNumber")
    suspend fun getUser(mobileNumber : String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: UserEntity)

    @Query("UPDATE userentity SET rejectedRestaurants = :rejectedRestaurants WHERE mobileNumber = :mobileNumber")
    suspend fun updateRejectedRestaurants(mobileNumber: String, rejectedRestaurants: ArrayList<String>)
}