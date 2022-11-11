package com.example.workcare.common.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class UserDataModel(
    @PrimaryKey
    val email: String,
    val pwd: String,
    val phoneNumber: String,
    val name: String
) : Serializable