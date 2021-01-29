package com.zj.mygarden.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zj.mygarden.fragment.GardenFragment
import com.zj.mygarden.fragment.PlantListFragment

const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

class SunflowerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragments: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment.newInstance() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment.newInstance() }
    )

    override fun getItemCount(): Int = tabFragments.size

    override fun createFragment(position: Int): Fragment {
        return tabFragments[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}