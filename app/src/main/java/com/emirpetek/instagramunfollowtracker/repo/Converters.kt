package com.emirpetek.instagramunfollowtracker.repo

import androidx.room.TypeConverter
import com.emirpetek.instagramunfollowtracker.data.DataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    // List<DataItem>'i JSON formatına dönüştürme
    @TypeConverter
    fun fromDataItemList(dataItemList: List<DataItem>?): String? {
        return Gson().toJson(dataItemList)
    }

    // JSON formatındaki String'i List<DataItem>'e dönüştürme
    @TypeConverter
    fun toDataItemList(dataItemString: String?): List<DataItem>? {
        val listType = object : TypeToken<List<DataItem>>() {}.type
        return Gson().fromJson(dataItemString, listType)
    }
}