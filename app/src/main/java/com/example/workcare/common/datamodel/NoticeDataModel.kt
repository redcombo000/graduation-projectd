package com.example.workcare.common.datamodel

import androidx.room.*
import java.io.Serializable

@Entity
data class NoticeDataModel(
    @PrimaryKey
    val id: String,
    val title: String,
    val contents : String,
    val date: String,
    val isLeader: Boolean,
) : Serializable