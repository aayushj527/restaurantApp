package com.example.restaurantsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.restaurantsapp.data.local.entity.ReviewEntity
import com.example.restaurantsapp.data.local.entity.UserEntity
import com.example.restaurantsapp.data.local.typeConvertors.ArrayListConvertor
import com.example.restaurantsapp.data.local.typeConvertors.MapConvertor

@Database(entities = [UserEntity::class, ReviewEntity::class], version = 1)
@TypeConverters(ArrayListConvertor::class, MapConvertor::class)
abstract class Database : RoomDatabase() {
    abstract fun dao(): AppDao
}