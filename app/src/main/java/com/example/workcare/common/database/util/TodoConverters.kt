package com.example.workcare.common.database.util

import androidx.room.TypeConverter
import com.example.workcare.common.datamodel.TodoDataModel
import com.google.gson.Gson
import java.util.*

class TodoConverters {
    @TypeConverter
    fun listToJson(value: List<TodoDataModel>) : String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) : List<TodoDataModel> = Gson().fromJson(value, Array<TodoDataModel>::class.java).toList()

}