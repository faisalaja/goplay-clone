package com.goplay.home.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.goplay.core.network.data.model.result.Movie
import com.goplay.home.ui.holder.PosterViewHolder

class CategoryAdapter : PagingDataAdapter<Movie, PosterViewHolder>(DiffUtilCallBack) {
    object DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}