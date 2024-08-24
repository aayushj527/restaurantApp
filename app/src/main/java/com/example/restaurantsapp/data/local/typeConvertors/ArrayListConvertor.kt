package com.example.restaurantsapp.data.local.typeConvertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArrayListConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        return gson.fromJson(value, object : TypeToken<ArrayList<String>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>): String {
        return gson.toJson(list)
    }
}