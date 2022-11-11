package com.example.workcare.view.group.notice

import android.os.Bundle
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.NoticeDataModel
import com.example.workcare.common.util.ViewUtils
import com.example.workcare.databinding.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ActivityGroupNoticeAdd : BaseActivity() {

    private lateinit var mBinding : ActivityGroupNoticeAddBinding

    private var isLeader = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGroupNoticeAddBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        isLeader = intent.getBooleanExtra("isLeader", false)

    }
    override fun setEvent() {
        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnSave.setOnClickListener {
            save()
        }

        mBinding.root.setOnClickListener{
            ViewUtils.hideKeyboard(this@ActivityGroupNoticeAdd, currentFocus)
        }
    }

    private fun save(){

        val title = mBinding.etTitle.text.toString()
        val contents = mBinding.etContents.text.toString()
        val date = SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().time)

        GlobalScope.launch {
            val list = AppDatabase.getInstance().noticeDao().findAll()
            val data = NoticeDataModel("${list.size}", title, contents, date, isLeader)
            AppDatabase.getInstance().noticeDao().insert(data)
            finish()
        }

    }

}