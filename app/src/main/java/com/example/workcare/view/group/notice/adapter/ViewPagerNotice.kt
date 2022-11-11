package com.example.workcare.view.group.notice.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.workcare.common.BaseFragment
import com.example.workcare.view.group.notice.fragment.FragmentGroupNotice

class ViewPagerNotice(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private var list = ArrayList<BaseFragment>()

    init {
        list.add(FragmentGroupNotice.getInstance(true))
        list.add(FragmentGroupNotice.getInstance(false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}