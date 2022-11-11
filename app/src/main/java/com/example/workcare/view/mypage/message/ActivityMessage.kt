package com.example.workcare.view.mypage.message

import android.os.Bundle
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.MessageDataModel
import com.example.workcare.databinding.ActivityMessageListBinding
import com.example.workcare.view.mypage.message.adapter.AdapterMessageList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityMessage : BaseActivity() {

    private lateinit var mBinding : ActivityMessageListBinding
    private val adapter = AdapterMessageList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMessageListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()

        getMessage()
    }

    override fun init() {
        mBinding.recyclerViewMessage.adapter = adapter
    }

    override fun setEvent() {
        mBinding.rlBack.setOnClickListener {
            finish()
        }

    }

    private fun getMessage(){

        GlobalScope.launch {

            val list = AppDatabase.getInstance().messageData().findAll()
            if(list.isNotEmpty()){
                adapter.setList(list)
            }else {
                addMessage()
            }

        }
    }

    // fixme test data
    private fun addMessage(){

        GlobalScope.launch {

            val data1 = MessageDataModel("2022.11.09", "공지사항 확인 요청","확인 후 응답 바람")
            val data2 = MessageDataModel("2022.11.10", "대타 요청","문의드립니다. 답장 부탁드려요")
            val data3 = MessageDataModel("2022.11.11", "인수인계","인수인계 자료 누락관련 보고")
            val data4 = MessageDataModel("2022.11.12", "공지사항 확인 요청","확인 후 응답 바람")
            val data5 = MessageDataModel("2022.11.13", "대타 요청","문의드립니다. 답장 부탁드려요")
            val data6 = MessageDataModel("2022.11.14", "인수인계","인수인계 자료 누락관련 보고")

            AppDatabase.getInstance().messageData().insert(data1)
            AppDatabase.getInstance().messageData().insert(data2)
            AppDatabase.getInstance().messageData().insert(data3)
            AppDatabase.getInstance().messageData().insert(data4)
            AppDatabase.getInstance().messageData().insert(data5)
            AppDatabase.getInstance().messageData().insert(data6)

            getMessage()
        }

    }
}