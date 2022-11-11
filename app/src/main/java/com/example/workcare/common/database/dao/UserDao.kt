package com.example.workcare.common.database.dao

import androidx.room.*
import com.example.workcare.common.datamodel.UserDataModel

@Dao
abstract class UserDao : BaseDao<UserDataModel>(){

}