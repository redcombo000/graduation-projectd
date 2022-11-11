package com.example.workcare.view.mypage.info_modify

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.UserDataModel
import com.example.workcare.databinding.ActivityMyInfoModifyBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ActivityInfoModify : BaseActivity() {

    private lateinit var mBinding: ActivityMyInfoModifyBinding
    private var user: UserDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMyInfoModifyBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        GlobalScope.launch {
            val list = AppDatabase.getInstance().userDao().findAll()
            if (list.isNotEmpty()) {
                user = list[0]
                MainScope().launch {
                    mBinding.etName.setText(user?.name)
                    mBinding.etEmail.setText(user?.email)
                    mBinding.etPhoneNumber.setText(user?.phoneNumber)
                }
            }
        }

    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.etName.addTextChangedListener(textListener)
        mBinding.etEmail.addTextChangedListener(textListener)
        mBinding.etPhoneNumber.addTextChangedListener(textListener)

        mBinding.btnModify.setOnClickListener {
            save()
        }
    }

    private fun save() {

        val name = mBinding.etName.text.toString()
        val email = mBinding.etEmail.text.toString()
        val phoneNumber = mBinding.etPhoneNumber.text.toString()
        val pwd = if (user == null) user!!.pwd else ""

        val modifyData = UserDataModel(email, pwd, phoneNumber, name)
        GlobalScope.launch {
            AppDatabase.getInstance().userDao().deleteAll()
            AppDatabase.getInstance().userDao().insert(modifyData)

            finish()
        }
    }

    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            mBinding.btnModify.isSelected = true
        }
    }

}