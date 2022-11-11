package com.example.workcare.common.datamodel

import androidx.room.*

@Entity
data class MessageDataModel(
    @PrimaryKey
    val date: String,
    val title: String,
    val contents: String
)