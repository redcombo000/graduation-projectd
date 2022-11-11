package com.example.workcare.view.group.todo

import android.animation.ValueAnimator
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.TodoDataModel
import com.example.workcare.common.datamodel.UserDataModel
import com.example.workcare.common.util.ViewUtils
import com.example.workcare.databinding.ActivityTodoAddBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ActivityGroupTodoAdd : BaseActivity() {

    private lateinit var mBinding : ActivityTodoAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTodoAddBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {


    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnRepeat01.setOnClickListener {
            checkRepeat(1)
        }

        mBinding.btnRepeat02.setOnClickListener {
            checkRepeat(2)
        }

        mBinding.btnRepeat03.setOnClickListener {
            checkRepeat(3)
        }

        mBinding.btnRepeat04.setOnClickListener {
            checkRepeat(4)
        }

        mBinding.imgPicture.setOnClickListener{
            mBinding.imgPicture.isSelected = !mBinding.imgPicture.isSelected
            check(mBinding.imgPicture.isSelected, mBinding.imgPicture)
        }

        mBinding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save(){

        val date = SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().time)
        val title = mBinding.etTitle.text.toString()
        val contents = mBinding.etContents.text.toString()
        val repeat = if(mBinding.btnRepeat01.isSelected) {
            1
        } else if(mBinding.btnRepeat02.isSelected){
            2
        } else if(mBinding.btnRepeat03.isSelected){
            3
        } else {
            4
        }
        val userList = arrayListOf<UserDataModel>()
        val endTime = ""
        val isPicture = mBinding.imgPicture.isSelected

        GlobalScope.launch {
            val list = AppDatabase.getInstance().todoDao().findAll()
            val todo = TodoDataModel("${list.size}", date, title, contents, repeat, userList, arrayListOf(), endTime, isPicture, false)
            AppDatabase.getInstance().todoDao().insert(todo)
            finish()
        }

    }

    private fun checkRepeat(repeat : Int){
        ViewUtils.hideKeyboard(this@ActivityGroupTodoAdd, mBinding.etContents)
        mBinding.btnRepeat01.isSelected = repeat == 1
        mBinding.btnRepeat02.isSelected = repeat == 2
        mBinding.btnRepeat03.isSelected = repeat == 3
        mBinding.btnRepeat04.isSelected = repeat == 4
    }

    private fun check(isChecked : Boolean, view : LottieAnimationView){
        if(isChecked){
            // on
            val animator = ValueAnimator.ofFloat(0f, 0.5f)
            animator.duration = 500
            animator.addUpdateListener {
                val value: Float = it.animatedValue as Float
                view.progress = value
            }
            animator.start()
        }else {
            // off

            val animator = ValueAnimator.ofFloat(0.5f, 1f)
            animator.duration = 500
            animator.addUpdateListener {
                val value: Float = it.animatedValue as Float
                view.progress = value
            }
            animator.start()
        }
    }
}