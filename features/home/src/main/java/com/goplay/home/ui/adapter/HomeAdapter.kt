package com.goplay.home.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.Movie
import com.goplay.home.data.Categories
import com.goplay.home.databinding.ItemCategoriesBinding
import com.goplay.home.ui.holder.HomeViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeAdapter(private val context: FragmentActivity?) : RecyclerView.Adapter<HomeViewHolder>() {

    var categories: List<Categories>? = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        categories?.get(position).let { category ->
            holder.bind(category)

            setCategoriesRecycler(
                context = context,
                holder.binding,
                pagingDataFlow = category?.pagingFlow
            )
        }
    }

    override fun getItemCount() = categories?.size ?: 0

    private fun setCategoriesRecycler(
        context: FragmentActivity?,
        binding: ItemCategoriesBinding,
        pagingDataFlow: Flow<PagingData<Movie>>?
    ) {
        val categoryAdapter = CategoryAdapter()

        context?.lifecycleScope?.launch(Dispatchers.IO) {
            pagingDataFlow?.onEach {
                categoryAdapter.submitData(it)
            }?.collect()
        }
        binding.showLoading = true
        CoroutineScope(Dispatchers.Main).launch {
            delay(1_500)
            binding.showLoading = false
        }
        binding.categoryListItem.rvMain.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = categoryAdapter
            setHasFixedSize(true)
            LinearSnapHelper().attachToRecyclerView(this)
        }
    }
}
