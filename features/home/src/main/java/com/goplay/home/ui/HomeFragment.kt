package com.goplay.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.goplay.home.R
import com.goplay.home.databinding.HomeFragmentBinding
import com.goplay.home.utils.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val title by lazy {
        listOf(
            getString(R.string.title_movie),
            getString(R.string.title_tv_show),
            getString(R.string.title_people),
        )
    }

    private lateinit var homeBinding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = homeBinding.tabLayout
        val viewPager = homeBinding.viewPager

        viewPager.adapter = ViewPagerAdapter(requireParentFragment())
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = title[0]
                1 -> tab.text = title[1]
                2 -> tab.text = title[2]
            }
        }.attach()
    }
}