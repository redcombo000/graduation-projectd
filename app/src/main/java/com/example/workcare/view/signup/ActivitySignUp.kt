package com.example.workcare.view.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.UserDataModel
import com.example.workcare.databinding.ActivitySignupBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivitySignUp : BaseActivity() {

    private lateinit var mBinding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.etEmail.addTextChangedListener(textListener)
        mBinding.etPwd.addTextChangedListener(textListener)
        mBinding.etPwd2.addTextChangedListener(textListener)
        mBinding.etPhoneNumber.addTextChangedListener(textListener)
        mBinding.etName.addTextChangedListener(textListener)
    }

    override fun setEvent() {

        mBinding.btnSignUp.setOnClickListener {
            if(mBinding.btnSignUp.isSelected){
                signup()
            }else {
                Toast.makeText(this@ActivitySignUp, "정보를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.imBack.setOnClickListener {
            finish()
        }
    }


    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            mBinding.btnSignUp.isSelected =
                mBinding.etEmail.text.toString().isNotEmpty()
                        && mBinding.etPwd.text.toString().isNotEmpty()
                        && mBinding.etPwd2.text.toString().isNotEmpty()
                        && mBinding.etPhoneNumber.text.toString().isNotEmpty()
                        && mBinding.etName.text.toString().isNotEmpty()
        }
    }

    private fun signup(){
        GlobalScope.launch {
            val user = UserDataModel(mBinding.etEmail.text.toString(), mBinding.etPwd.text.toString(), mBinding.etPhoneNumber.text.toString(), mBinding.etName.text.toString())
            AppDatabase.getInstance().userDao().insert(user)
            finish()
        }
    }
}