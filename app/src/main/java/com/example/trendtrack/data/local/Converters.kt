package com.example.trendtrack.data.local

import androidx.room.TypeConverter
import com.example.trendtrack.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return gson.toJson(source)
    }
    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        if (sourceString == null) return null
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(sourceString, type)
    }
} 