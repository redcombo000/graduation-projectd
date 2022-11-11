package com.example.workcare.common.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReplyDataModel(
    @PrimaryKey
    val date: String,
    val roomId: String,
    val name: String,
    val contents: String
)