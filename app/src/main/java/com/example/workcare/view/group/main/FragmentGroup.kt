package com.example.workcare.view.group.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workcare.common.BaseFragment
import com.example.workcare.common.database.AppDatabase
import com.example.workcare.databinding.FragmentGroupBinding
import com.example.workcare.view.group.main.adapter.AdapterGroupList
import com.example.workcare.view.group.create.ActivityGroupCreate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class FragmentGroup : BaseFragment() {

    private lateinit var mBinding: FragmentGroupBinding
    private val adapter = AdapterGroupList()

    companion object {
        fun getInstance(): FragmentGroup {
            val fragment = FragmentGroup()
            val args = Bundle()
//        args.putString(KEY, key)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
//            key = arguments?.getString(key)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGroupBinding.inflate(layoutInflater, container, false)

        init()
        setEvent()

        return mBinding.root
    }

    override fun init() {
        mBinding.recyclerViewGroup.adapter = adapter
        getGroupList()
    }

    override fun setEvent() {

        mBinding.btnCreate.setOnClickListener {
            startActivity(Intent(activity, ActivityGroupCreate::class.java))
        }

    }

    private fun getGroupList() {

        GlobalScope.launch {

            val list = AppDatabase.getInstance().groupDao().findAll()

            MainScope().launch {

                if (list.isNotEmpty()) {
                    adapter.setList(list)
                    mBinding.tvNoData.visibility = View.GONE
                } else {
                    mBinding.tvNoData.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        getGroupList()
    }
}