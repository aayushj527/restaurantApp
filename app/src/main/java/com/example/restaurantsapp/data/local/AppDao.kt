package com.example.restaurantsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantsapp.data.local.entity.UserEntity

@Dao
interface AppDao {
//    @Query("SELECT EXISTS(SELECT * FROM userentity WHERE mobileNumber = :mobileNumber)")
//    fun userExists(mobileNumber : String) : Boolean

    @Query("SELECT * FROM userentity WHERE mobileNumber = :mobileNumber")
    suspend fun getUser(mobileNumber : String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun register(user: UserEntity)
}