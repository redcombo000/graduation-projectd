package com.example.workcare.view.group.info

import android.os.Bundle
import android.widget.Toast
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.datamodel.UserDataModel
import com.example.workcare.databinding.ActivityMemberInfoBinding
import com.example.workcare.view.group.info.adapter.AdapterGroupMemberList
import com.example.workcare.view.group.info.dialog.DialogMessage

class ActivityMemberInfo : BaseActivity() {

    private lateinit var mBinding : ActivityMemberInfoBinding
    private var adapter = AdapterGroupMemberList()

    private lateinit var user : UserDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMemberInfoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.recyclerViewTodoList.adapter = adapter
        getMemberInfo()
    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnDelete.setOnClickListener {
            // 삭제
            Toast.makeText(this@ActivityMemberInfo, "강퇴되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        mBinding.btnMessage.setOnClickListener {
            DialogMessage{
                Toast.makeText(this@ActivityMemberInfo, "메세지", Toast.LENGTH_SHORT).show()
            }.show(supportFragmentManager, "msg")
        }

        mBinding.btnEmergency.setOnClickListener {
            Toast.makeText(this@ActivityMemberInfo, "긴급 메세지", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMemberInfo(){

        try {

            val data = intent.getSerializableExtra("user") as UserDataModel
            mBinding.tvName.text = data.name
            mBinding.tvEmail.text = data.email
            mBinding.tvPhoneNumber.text = data.phoneNumber

            // todo 체크리스트
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}