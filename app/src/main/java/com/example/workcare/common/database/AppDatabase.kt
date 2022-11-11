package com.example.workcare.common.database

import android.content.Context
import androidx.room.*
import com.example.workcare.common.database.dao.*
import com.example.workcare.common.database.util.TodoConverters
import com.example.workcare.common.database.util.UserConverters
import com.example.workcare.common.datamodel.*

/**
 * Room Database
 */
@Database(
    entities = [UserDataModel::class, TodoDataModel::class, GroupDataModel::class, MessageDataModel::class, ReplyDataModel::class, NoticeDataModel::class],
    version = 9
)
@TypeConverters(UserConverters::class, TodoConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun groupDao(): GroupDao

    abstract fun messageData(): MessageDao

    abstract fun todoDao(): TodoDao

    abstract fun replyDao(): ReplyDao

    abstract fun noticeDao(): NoticeDao

    // todo DAO 추가


    /**
     * Application 단에서 createInstance 호출할 것!
     */
    companion object {

        private lateinit var INSTANCE: AppDatabase

        fun createInstance(context: Context) {

            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "sample_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }

        }

        fun getInstance(): AppDatabase {
            return INSTANCE
        }

    }

}