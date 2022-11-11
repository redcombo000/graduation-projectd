package com.example.workcare.view.group.create

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.common.datamodel.GroupDataModel
import com.example.workcare.databinding.ActivityGroupCreateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityGroupCreate : BaseActivity() {

    private lateinit var mBinding : ActivityGroupCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGroupCreateBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.etGroupName.addTextChangedListener(textListener)
    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save(){

        val group = GroupDataModel(mBinding.etGroupName.text.toString(), arrayListOf(), arrayListOf())
        GlobalScope.launch {
            AppDatabase.getInstance().groupDao().insert(group)
            finish()
        }

    }

    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            mBinding.btnSave.isSelected =
                mBinding.etGroupName.text.toString().isNotEmpty()
        }
    }
}