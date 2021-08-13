package com.goplay.home.ui.page

import MovieType
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.goplay.core.utils.Status
import com.goplay.home.R
import com.goplay.home.data.Categories
import com.goplay.home.databinding.PageMainFragmentBinding
import com.goplay.home.ui.adapter.HomeAdapter
import com.goplay.home.ui.viewmodel.MovieViewModel
import com.goplay.home.utils.ItemListDecoration
import com.goplay.home.utils.capitalize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var pageMainBinding: PageMainFragmentBinding
    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel by viewModels<MovieViewModel>()

    companion object {
        fun newInstance() = MovieFragment()
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
        homeAdapter = HomeAdapter()
        return pageMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupView()
    }

    private fun setupObserver() {
        val movieCategory = mutableListOf<Categories>()
        var emitNumber = 0
        val title = mutableListOf(
            MovieType.NOW_PLAYING,
            MovieType.TOP_RATED,
            MovieType.UPCOMING,
            MovieType.POPULAR
        )
        homeViewModel.movies.observe(viewLifecycleOwner) {
            it.map { dataFlow ->
                lifecycleScope.launch {
                    dataFlow.collect { resource ->
                        delay(300)
                        when (resource.status) {
                            Status.LOADING -> {
                                if (emitNumber < 1) pageMainBinding.showLoading = true
                            }
                            Status.SUCCESS -> {
                                pageMainBinding.showLoading = false
                                movieCategory.add(
                                    Categories(
                                        title = capitalize(
                                            title[emitNumber],
                                            " ${resources.getString(R.string.title_movie)}"
                                        ),
                                        movies = resource.data?.movies
                                    )
                                )
                                emitNumber = emitNumber.plus(1)
                            }
                            Status.ERROR -> {
                                pageMainBinding.showLoading = false
                            }
                            else -> throw IllegalAccessException("Illegal action!")
                        }
                    }
                    homeAdapter.categories = movieCategory
                }
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