package com.example.restaurantsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantsapp.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): AppDao
}