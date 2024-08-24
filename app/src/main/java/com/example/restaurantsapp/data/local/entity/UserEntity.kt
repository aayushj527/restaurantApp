package com.example.restaurantsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(false)
    var mobileNumber: String,
    var name: String,
    var pin: String,
    var rejectedRestaurants: ArrayList<String>? = null
)