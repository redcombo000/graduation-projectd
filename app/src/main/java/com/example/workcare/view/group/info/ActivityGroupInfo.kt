package com.example.workcare.view.group.info

import android.os.Bundle
import android.widget.Toast
import com.example.workcare.common.BaseActivity
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.databinding.ActivityGroupInfoBinding
import com.example.workcare.view.group.info.adapter.AdapterGroupMemberList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityGroupInfo : BaseActivity() {

    private lateinit var mBinding : ActivityGroupInfoBinding
    private var adapter = AdapterGroupMemberList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityGroupInfoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        init()
        setEvent()
    }

    override fun init() {

        mBinding.recyclerViewGroupMember.adapter = adapter
        getGroupInfo()
    }

    override fun setEvent() {

        mBinding.rlBack.setOnClickListener {
            finish()
        }

        mBinding.btnDelete.setOnClickListener {
            // 삭제
            Toast.makeText(this@ActivityGroupInfo, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }

        mBinding.btnInvite.setOnClickListener {
            // 초대
        }
    }

    private fun getGroupInfo(){

        GlobalScope.launch {

            val list = AppDatabase.getInstance().groupDao().findAll()
            if(list.isNotEmpty()){

                val data = list[0]
                val memberList = data.userList

                mBinding.tvMemberCount.text = "${memberList.size}명"
                mBinding.tvGroupName.text = data.groupName
                adapter.setList(memberList)
            }
        }

    }
}