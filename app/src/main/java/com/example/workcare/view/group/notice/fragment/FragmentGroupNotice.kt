package com.example.workcare.view.group.notice.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workcare.common.BaseFragment
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.databinding.FragmentGroupNoticeBinding
import com.example.workcare.view.group.notice.ActivityGroupNoticeAdd
import com.example.workcare.view.group.notice.fragment.adapter.AdapterGroupNoticeList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FragmentGroupNotice : BaseFragment() {

    private lateinit var mBinding: FragmentGroupNoticeBinding
    private val adapter = AdapterGroupNoticeList()

    private var isLeader = false

    companion object {

        val KEY = "leader"

        fun getInstance(isLeader: Boolean): FragmentGroupNotice {
            val fragment = FragmentGroupNotice()
            val args = Bundle()
            args.putBoolean(KEY, isLeader)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            isLeader = if(arguments == null || arguments?.getBoolean(KEY) == null) false else requireArguments().getBoolean(KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGroupNoticeBinding.inflate(layoutInflater, container, false)

        init()
        setEvent()

        return mBinding.root
    }

    override fun init() {
        mBinding.recyclerViewGroup.adapter = adapter
        getGroupNoticeList()
    }

    override fun setEvent() {

        mBinding.btnCreate.setOnClickListener {
            val intent = Intent(activity, ActivityGroupNoticeAdd::class.java)
            intent.putExtra("isLeader", isLeader)
            startActivity(intent)
        }

    }

    private fun getGroupNoticeList() {
        GlobalScope.launch {

            val list = AppDatabase.getInstance().noticeDao().findAll()
            val leaderList = list.filter { it.isLeader }.toList()
            val memberList = list.filter { !it.isLeader }.toList()

            MainScope().launch {

                adapter.setList(if(isLeader) leaderList else memberList)

            }

        }
    }

    override fun onResume() {
        super.onResume()
        getGroupNoticeList()
    }
}