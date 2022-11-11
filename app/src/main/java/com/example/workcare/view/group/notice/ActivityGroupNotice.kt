package com.example.workcare.view.group.notice

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.workcare.common.BaseActivity
import com.example.workcare.databinding.ActivityGroupNoticeBinding
import com.example.workcare.view.group.notice.adapter.ViewPagerNotice
import java.util.*

class ActivityGroupNotice : BaseActivity() {

    private lateinit var mBinding : ActivityGroupNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGroupNoticeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.viewpagerNotice.adapter = ViewPagerNotice(this@ActivityGroupNotice)

    }
    override fun setEvent() {
        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnGroupLeader.setOnClickListener {
            mBinding.viewpagerNotice.currentItem = 0
        }

        mBinding.btnGroupMember.setOnClickListener {
            mBinding.viewpagerNotice.currentItem = 1
        }

        mBinding.viewpagerNotice.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.btnGroupLeader.isSelected = position == 0
                mBinding.btnGroupMember.isSelected = position == 1
            }
        })
    }

}