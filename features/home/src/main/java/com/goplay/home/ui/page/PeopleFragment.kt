package com.goplay.home.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.goplay.home.R
import com.goplay.home.databinding.PageMainFragmentBinding
import com.goplay.home.ui.adapter.PeopleAdapter
import com.goplay.home.ui.viewmodel.PeopleViewModel
import com.goplay.home.utils.ItemListDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private lateinit var pageMainBinding: PageMainFragmentBinding
    private lateinit var peopleAdapter: PeopleAdapter
    private val peopleViewModel by viewModels<PeopleViewModel>()

    companion object {
        fun newInstance() = PeopleFragment()
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
        peopleAdapter = PeopleAdapter()
        return pageMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupView()
    }

    private fun setupObserver() {
        peopleViewModel.peoplePopular.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it.collect { pagingData ->
                    peopleAdapter.submitData(lifecycle, pagingData)
                }
            }
        }
    }

    private fun setupView() {
        val itemListDecoration = ItemListDecoration()
        itemListDecoration.apply {
            itemListDecoration(requireContext())
            orientationStyle(4)
        }

        pageMainBinding.rvContainer.rvMain.apply {
            layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = peopleAdapter
            setHasFixedSize(true)
            addItemDecoration(itemListDecoration)
        }
    }
}