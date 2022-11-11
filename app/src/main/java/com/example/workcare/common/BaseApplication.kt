package com.example.workcare.common

import android.app.Application
import com.example.workcare.common.database.AppDatabase

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase.createInstance(this)
    }

}