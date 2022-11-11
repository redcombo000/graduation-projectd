package com.example.workcare.common.database.util

import androidx.room.TypeConverter
import com.example.workcare.common.datamodel.UserDataModel
import com.google.gson.Gson
import java.util.*

class UserConverters {
    @TypeConverter
    fun listToJson(value: List<UserDataModel>) : String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<UserDataModel> = Gson().fromJson(value, Array<UserDataModel>::class.java).toList()

}