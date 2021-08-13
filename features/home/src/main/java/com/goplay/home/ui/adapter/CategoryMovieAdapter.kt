package com.goplay.home.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.Movie
import com.goplay.home.ui.holder.PosterViewHolder

class CategoryMovieAdapter : RecyclerView.Adapter<PosterViewHolder>() {

    var movies: List<Movie>? = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PosterViewHolder {
        return PosterViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        movies?.get(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

}