package com.example.workcare.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun init()

    abstract fun setEvent()

}