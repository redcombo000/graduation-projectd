package com.example.workcare.view.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.util.DataFactory
import com.example.workcare.databinding.ActivityLoginBinding
import com.example.workcare.view.main.ActivityMain
import com.example.workcare.view.signup.ActivitySignUp

class ActivityLogin : BaseActivity() {

    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()

    }

    override fun init() {
        mBinding.etEmail.addTextChangedListener(textListener)
        mBinding.etPwd.addTextChangedListener(textListener)
    }

    override fun setEvent() {

        // 로그인
        mBinding.btnLogin.setOnClickListener {
            startActivity(Intent(this@ActivityLogin, ActivityMain::class.java))
            finishAffinity()
        }

        // 회원가입
        mBinding.tvSignUp.setOnClickListener {
            startActivity(Intent(this@ActivityLogin, ActivitySignUp::class.java))
        }

        // 아이디찾기
        mBinding.tvFindId.setOnClickListener {
            // todo
        }

        // 비밀번호 찾기
        mBinding.tvFindPwd.setOnClickListener {
            // todo
        }

        // 테스트 데이터 추가
        mBinding.btnTestData.setOnClickListener {
            DataFactory().initData()
        }

    }

    // 회원정보 있는경우 자동로그인
//    private fun checkUser(){
//        GlobalScope.launch {
//
//            val list = AppDatabase.getInstance().userDao().findAll()
//            if(list.isNotEmpty()){
//                CommonConfig.selectedUser = list[0]
//                startActivity(Intent(this@ActivityLogin, ActivityMain::class.java))
//            }
//        }
//    }


    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            mBinding.btnLogin.isSelected =
                mBinding.etEmail.text.toString().isNotEmpty()
                        && mBinding.etPwd.text.toString().isNotEmpty()
        }
    }

}