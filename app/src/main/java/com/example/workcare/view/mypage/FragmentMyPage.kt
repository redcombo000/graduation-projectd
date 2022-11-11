package com.example.workcare.view.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workcare.common.BaseFragment
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.databinding.FragmentMyPageBinding
import com.example.workcare.view.login.ActivityLogin
import com.example.workcare.view.mypage.alarm.ActivityAlarm
import com.example.workcare.view.mypage.info_modify.ActivityInfoModify
import com.example.workcare.view.mypage.message.ActivityMessage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FragmentMyPage : BaseFragment() {

    private lateinit var mBinding: FragmentMyPageBinding

    companion object {
        fun getInstance(): FragmentMyPage {
            val fragment = FragmentMyPage()
            val args = Bundle()
//        args.putString(KEY, key)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
//            key = arguments?.getString(key)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMyPageBinding.inflate(layoutInflater, container, false)

        init()
        setEvent()

        return mBinding.root
    }

    override fun init() {

        GlobalScope.launch {
            val list = AppDatabase.getInstance().userDao().findAll()
            if (list.isNotEmpty()) {
                val user = list[0]
                MainScope().launch {
                    mBinding.tvName.text = user.name
                    mBinding.tvEmail.text = user.email
                    mBinding.tvPhoneNumber.text = user.phoneNumber
                }
            }
        }

    }

    override fun setEvent() {

        // 정보 수정
        mBinding.btnModify.setOnClickListener {
            startActivity(Intent(activity, ActivityInfoModify::class.java))
        }

        // 알림 설정
        mBinding.rlAlarm.setOnClickListener {
            startActivity(Intent(activity, ActivityAlarm::class.java))
        }

        // 메세지
        mBinding.rlMessage.setOnClickListener {
            startActivity(Intent(activity, ActivityMessage::class.java))
        }

        // 비밀번호 변경
        mBinding.rlChangePwd.setOnClickListener {

        }

        // 로그아웃
        mBinding.rlLogout.setOnClickListener {
            startActivity(Intent(activity, ActivityLogin::class.java))
            activity?.finish()
        }

        // 회원탈퇴
        mBinding.rlWithDraw.setOnClickListener {
            startActivity(Intent(activity, ActivityLogin::class.java))
            activity?.finish()
        }
    }

    override fun onResume() {
        super.onResume()

        init()
    }
}