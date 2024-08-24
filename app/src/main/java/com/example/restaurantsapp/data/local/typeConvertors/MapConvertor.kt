package com.example.restaurantsapp.data.local.typeConvertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): HashMap<String, String> {
        return gson.fromJson(value, object : TypeToken<HashMap<String, String>>() {}.type)
    }

    @TypeConverter
    fun fromArrayList(map: HashMap<String, String>): String {
        return gson.toJson(map)
    }
}