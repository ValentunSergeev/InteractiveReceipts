package com.valentun.interactiverecipes.ui.activity.cooking

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.valentun.interactiverecipes.data.pojo.Section
import com.valentun.interactiverecipes.ui.fragment.section.SectionFragment

class SectionAdapter(activity: FragmentActivity,
                     private val data: List<Section>) : FragmentStateAdapter(activity) {

    override fun getItemCount() = data.size

    override fun createFragment(position: Int): Fragment {
        val section = data[position]

        val isLast = position == data.size - 1

        return SectionFragment.newInstance(section, isLast)
    }
}