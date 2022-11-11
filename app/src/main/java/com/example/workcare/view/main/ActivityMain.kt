package com.example.workcare.view.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.workcare.common.BaseActivity
import com.example.workcare.databinding.ActivityMainBinding
import com.example.workcare.view.main.adapter.ViewPagerMain

class ActivityMain : BaseActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.viewPagerMain.adapter = ViewPagerMain(this)

        mBinding.viewPagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.llGroup.isSelected = position == 0
                mBinding.llMyPage.isSelected = position == 1
            }
        })

    }

    override fun setEvent() {

        mBinding.llGroup.setOnClickListener {
            mBinding.viewPagerMain.currentItem = 0
        }

        mBinding.llMyPage.setOnClickListener {
            mBinding.viewPagerMain.currentItem = 1
        }

    }

}