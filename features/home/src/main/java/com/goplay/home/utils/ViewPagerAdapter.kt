package com.goplay.home.utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goplay.home.ui.page.MovieFragment
import com.goplay.home.ui.page.PeopleFragment
import com.goplay.home.ui.page.TvShowFragment

class ViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment.newInstance()
            1 -> TvShowFragment.newInstance()
            2 -> PeopleFragment.newInstance()
            else -> MovieFragment.newInstance()
        }
    }
}