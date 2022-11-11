package com.example.workcare.view.group.todo

import android.os.Bundle
import android.view.View
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.ReplyDataModel
import com.example.workcare.common.datamodel.TodoDataModel
import com.example.workcare.common.util.ViewUtils
import com.example.workcare.databinding.ActivityTodoDetailBinding
import com.example.workcare.view.group.todo.adapter.AdapterReply
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ActivityGroupTodoDetail : BaseActivity() {

    private lateinit var mBinding : ActivityTodoDetailBinding

    private lateinit var item : TodoDataModel
    private var adapterReply = AdapterReply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTodoDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        try{

            item = intent.getSerializableExtra("item") as TodoDataModel
            mBinding.tvTitle.text = item.title

            mBinding.replyLayer.visibility = if(item.isComplete) View.VISIBLE else View.GONE
            mBinding.btnSave.visibility = if(item.isComplete) View.GONE else View.VISIBLE

            if(item.isComplete){
                mBinding.etContents.isEnabled = false
                mBinding.etContents.setText(item.contents)
            }
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

        mBinding.btnSave.setOnClickListener {
            save()
        }

        mBinding.sendReply.setOnClickListener{
            saveReply()
        }
    }

    private fun setReply(){
        GlobalScope.launch {
            val list = AppDatabase.getInstance().replyDao().findAll()
            MainScope().launch {
                val list_ = list.filter { it.roomId == item.roomId }
                adapterReply.setList(list_)
            }
        }
    }

    private fun save(){
        GlobalScope.launch {
            item.contents = mBinding.etContents.text.toString()
            item.isComplete = true
            AppDatabase.getInstance().todoDao().update(item)
            finish()
        }
    }

    private fun saveReply(){

        GlobalScope.launch {
            val userList = AppDatabase.getInstance().userDao().findAll()
            val r = Random().nextInt(userList.size-1)
            val userName = userList[r].name

            val date = SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().time)
            val data = ReplyDataModel(date, item.roomId, userName, mBinding.etReply.text.toString())
            AppDatabase.getInstance().replyDao().insert(data)

            MainScope().launch {
                ViewUtils.hideKeyboard(this@ActivityGroupTodoDetail, mBinding.etReply)
                mBinding.etReply.setText("")
                setReply()
            }
        }

    }

}