package com.example.workcare.common.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TodoDataModel(
    @PrimaryKey
    val roomId: String,
    val date: String,
    val title: String,
    var contents: String,
    val repeat: Int,
    @ColumnInfo(name = "userList")
    val userList: List<UserDataModel>,
    val completeUserList: List<UserDataModel>,
    var endTime: String,
    var isPicture: Boolean,
    var isComplete : Boolean
) : Serializable