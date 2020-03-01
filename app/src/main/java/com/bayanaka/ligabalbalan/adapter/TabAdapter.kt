package com.bayanaka.ligabalbalan.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabAdapter(
    fm: FragmentManager,
    var listOfFragment: List<Fragment>,
    var listOfTitle: List<String>
) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        return listOfFragment[position]
    }

    override fun getCount(): Int {
        return listOfFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listOfTitle[position]
    }
}