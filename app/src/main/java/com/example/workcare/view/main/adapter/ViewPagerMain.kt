package com.example.workcare.view.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.workcare.common.BaseFragment
import com.example.workcare.view.group.main.FragmentGroup
import com.example.workcare.view.mypage.FragmentMyPage

class ViewPagerMain(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private var list = ArrayList<BaseFragment>()

    init {
        list.add(FragmentGroup.getInstance())
        list.add(FragmentMyPage.getInstance())
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}