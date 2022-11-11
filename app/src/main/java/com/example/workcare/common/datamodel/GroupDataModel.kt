package com.example.workcare.common.datamodel

import androidx.room.*

@Entity
data class GroupDataModel(
    @PrimaryKey
    val groupName: String,
    @ColumnInfo(name = "userList")
    val userList: List<UserDataModel>,
    @ColumnInfo(name = "todoList")
    val todoList: List<TodoDataModel>
)