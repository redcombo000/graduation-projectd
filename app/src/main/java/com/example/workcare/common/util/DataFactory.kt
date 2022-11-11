package com.example.workcare.common.util

import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.GroupDataModel
import com.example.workcare.common.datamodel.TodoDataModel
import com.example.workcare.common.datamodel.UserDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 테스트데이터 초기화 유틸
 */
class DataFactory {

    fun initData(){

        initMember()
        initGroup()
    }

    fun initGroup() {

        val user1 = UserDataModel("tester92@gmail.com", "1234", "010-2333-1222", "테스터")
        val user2 = UserDataModel("payment1122@gmail.com", "1234", "010-2243-1422", "꼬물이")
        val user3 = UserDataModel("pulldown_9204@gmail.com", "1234", "010-2443-1151", "홍길동")

        val userList = arrayListOf<UserDataModel>()
        userList.add(user1)
        userList.add(user2)
        userList.add(user3)

        val todoList = arrayListOf<TodoDataModel>()

        val group1 = GroupDataModel("따릉이", userList, todoList)

        GlobalScope.launch {

            AppDatabase.getInstance().groupDao().deleteAll()
            AppDatabase.getInstance().groupDao().insert(group1)

        }
    }

    // 사용자
    fun initMember() {

        val user1 = UserDataModel("tester92@gmail.com", "1234", "010-2333-1222", "테스터")
        val user2 = UserDataModel("payment1122@gmail.com", "1234", "010-2243-1422", "꼬물이")
        val user3 = UserDataModel("pulldown_9204@gmail.com", "1234", "010-2443-1151", "홍길동")

        GlobalScope.launch {

            AppDatabase.getInstance().userDao().deleteAll()
            AppDatabase.getInstance().userDao().insert(user1)
            AppDatabase.getInstance().userDao().insert(user2)
            AppDatabase.getInstance().userDao().insert(user3)
        }

    }
}