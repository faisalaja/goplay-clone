package com.goplay.home.ui.page

import TvShowType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.goplay.home.R
import com.goplay.home.data.Categories
import com.goplay.home.databinding.PageMainFragmentBinding
import com.goplay.home.ui.adapter.HomeAdapter
import com.goplay.home.ui.viewmodel.MovieViewModel
import com.goplay.home.utils.ItemListDecoration
import com.goplay.home.utils.capitalize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private lateinit var pageMainBinding: PageMainFragmentBinding
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel by viewModels<MovieViewModel>()

    companion object {
        fun newInstance() = TvShowFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pageMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.page_main_fragment,
            container, false
        )
        homeAdapter = HomeAdapter(activity)
        return pageMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupView()
    }

    private fun setupObserver() {
        val movieCategory = mutableListOf<Categories>()
        val title = mutableListOf(
            TvShowType.AIRING_TODAY,
            TvShowType.ON_THE_AIR,
            TvShowType.TOP_RATED,
            TvShowType.POPULAR,
        )
        homeViewModel.tvShow.observe(viewLifecycleOwner) {
            it.mapIndexed { index, flowData ->
                movieCategory.add(
                    Categories(
                        title = capitalize(
                            title[index],
                            " ${resources.getString(R.string.title_tv_show)}"
                        ),
                        pagingFlow = flowData
                    )
                )
                homeAdapter.categories = movieCategory
            }
        }
    }

    private fun setupView() {
        val itemListDecoration = ItemListDecoration()
        itemListDecoration.itemListDecoration(requireContext())

        pageMainBinding.rvContainer.rvMain.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
            addItemDecoration(itemListDecoration)
        }
    }
}