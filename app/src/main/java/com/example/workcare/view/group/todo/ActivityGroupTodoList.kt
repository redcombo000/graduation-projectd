package com.example.workcare.view.group.todo

import android.content.Intent
import android.os.Bundle
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.databinding.ActivityTodoBinding
import com.example.workcare.view.group.todo.adapter.AdapterTodoList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ActivityGroupTodoList : BaseActivity() {

    private lateinit var mBinding : ActivityTodoBinding

    private val calendar = Calendar.getInstance()
    private val adapterComplete = AdapterTodoList()
    private val adapterIncomplete = AdapterTodoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.recyclerViewComplete.adapter = adapterComplete
        mBinding.recyclerViewIncomplete.adapter = adapterIncomplete

    }
    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.imgPrev.setOnClickListener{
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            setDate()
        }

        mBinding.imgNext.setOnClickListener{
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            setDate()
        }

        mBinding.btnAdd.setOnClickListener {
            startActivity(Intent(this@ActivityGroupTodoList, ActivityGroupTodoAdd::class.java))
        }
    }

    private fun getTodoList(){

        GlobalScope.launch {

            val list = AppDatabase.getInstance().todoDao().findAll()

            val completeList = list.filter { it.isComplete }.toList()
            val incompleteList = list.filter { !it.isComplete }.toList()

            MainScope().launch {
                adapterComplete.setList(completeList)
                adapterIncomplete.setList(incompleteList)
            }
        }

    }

    private fun setDate(){
        mBinding.tvDate.text = SimpleDateFormat("yyyy년 MM월 dd일").format(calendar.time)
    }

    override fun onResume() {
        super.onResume()
        setDate()
        getTodoList()
    }

}