package com.example.workcare.view.mypage.alarm

import android.animation.ValueAnimator
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.workcare.common.BaseActivity
import com.example.workcare.databinding.ActivityAlarmSettingBinding

class ActivityAlarm : BaseActivity() {

    private lateinit var mBinding : ActivityAlarmSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAlarmSettingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.rlReplyAlarm.setOnClickListener {

            mBinding.rlReplyAlarm.isSelected = !mBinding.rlReplyAlarm.isSelected
            check(mBinding.rlReplyAlarm.isSelected, mBinding.imgReply)
        }

        mBinding.rlMessageAlarm.setOnClickListener {

            mBinding.rlMessageAlarm.isSelected = !mBinding.rlMessageAlarm.isSelected
            check(mBinding.rlMessageAlarm.isSelected, mBinding.imgMessage)
        }

        mBinding.rlEmergencyAlarm.setOnClickListener {

            mBinding.rlEmergencyAlarm.isSelected = !mBinding.rlEmergencyAlarm.isSelected
            check(mBinding.rlEmergencyAlarm.isSelected, mBinding.imgEmergency)
        }



    }

    private fun check(isChecked : Boolean, view : LottieAnimationView){

        if(isChecked){
            // on
            val animator = ValueAnimator.ofFloat(0f, 0.5f)
            animator.duration = 500
            animator.addUpdateListener {
                val value: Float = it.animatedValue as Float
                view.progress = value
            }
            animator.start()
        }else {
            // off

            val animator = ValueAnimator.ofFloat(0.5f, 1f)
            animator.duration = 500
            animator.addUpdateListener {
                val value: Float = it.animatedValue as Float
                view.progress = value
            }
            animator.start()
        }


    }

}