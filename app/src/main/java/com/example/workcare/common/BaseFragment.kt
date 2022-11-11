package com.example.workcare.common

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun init()

    abstract fun setEvent()

}