package com.example.restaurantsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantsapp.data.local.entity.ReviewEntity
import com.example.restaurantsapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM user_table WHERE mobileNumber = :mobileNumber")
    suspend fun getUser(mobileNumber : String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: UserEntity)

    @Query("UPDATE user_table SET rejectedRestaurants = :rejectedRestaurants WHERE mobileNumber = :mobileNumber")
    suspend fun updateRejectedRestaurants(mobileNumber: String, rejectedRestaurants: ArrayList<String>)

    @Query("SELECT * FROM review_table")
    fun getReviews(): Flow<List<ReviewEntity>>

//    @Query("UPDATE review_table SET reviews = :reviews WHERE fsqId = :fsqId")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReviews(reviewEntity: ReviewEntity)
}