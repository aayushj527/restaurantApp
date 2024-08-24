package com.example.restaurantsapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("review_table")
data class ReviewEntity(
    @PrimaryKey(false)
    var fsqId: String,
    var reviews: HashMap<String, String>
)