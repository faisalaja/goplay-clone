package com.goplay.home.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.Movie
import com.goplay.home.data.Categories
import com.goplay.home.ui.holder.HomeViewHolder

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    var categories: List<Categories>? = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            Log.d("TAG", "value: $value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder.inflate(parent)

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        categories?.get(position).let { category ->
            holder.bind(category)

            setCategoriesRecycler(
                holder.itemView.context,
                holder.itemRecyclerView,
                categories?.get(position)?.movies
            )
        }
    }

    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }

    private fun setCategoriesRecycler(
        context: Context,
        recyclerView: RecyclerView,
        movies: List<Movie>?,
    ) {
        val categoryMovieAdapter = CategoryMovieAdapter()
        categoryMovieAdapter.movies = movies

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryMovieAdapter
            setHasFixedSize(true)
        }
    }
}