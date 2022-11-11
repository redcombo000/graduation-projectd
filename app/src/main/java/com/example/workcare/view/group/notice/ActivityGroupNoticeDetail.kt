package com.example.workcare.view.group.notice

import android.os.Bundle
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.NoticeDataModel
import com.example.workcare.common.datamodel.ReplyDataModel
import com.example.workcare.common.util.ViewUtils
import com.example.workcare.databinding.ActivityGroupNoticeDetailBinding
import com.example.workcare.view.group.todo.adapter.AdapterReply
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ActivityGroupNoticeDetail : BaseActivity() {

    private lateinit var mBinding : ActivityGroupNoticeDetailBinding

    private lateinit var item : NoticeDataModel
    private var adapterReply = AdapterReply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGroupNoticeDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        try{

            item = intent.getSerializableExtra("item") as NoticeDataModel
            mBinding.tvTitle.text = item.title
            mBinding.tvContents.text = item.contents
            mBinding.recyclerViewReply.adapter = adapterReply

            setReply()
        } catch (e : Exception){
            e.printStackTrace()
        }

    }
    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.sendReply.setOnClickListener{
            saveReply()
        }
    }

    private fun setReply(){
        GlobalScope.launch {
            val list = AppDatabase.getInstance().replyDao().findAll()
            MainScope().launch {
                val list_ = list.filter { it.roomId == item.id }
                adapterReply.setList(list_)
            }
        }
    }

    private fun saveReply(){

        GlobalScope.launch {
            val userList = AppDatabase.getInstance().userDao().findAll()
            val r = Random().nextInt(userList.size-1)
            val userName = userList[r].name

            val date = SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().time)
            val data = ReplyDataModel(date, item.id, userName, mBinding.etReply.text.toString())
            AppDatabase.getInstance().replyDao().insert(data)

            MainScope().launch {
                ViewUtils.hideKeyboard(this@ActivityGroupNoticeDetail, mBinding.etReply)
                mBinding.etReply.setText("")
                setReply()
            }
        }

    }

}