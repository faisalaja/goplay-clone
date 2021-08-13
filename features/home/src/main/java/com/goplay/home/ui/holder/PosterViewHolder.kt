package com.goplay.home.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.Movie
import com.goplay.home.R
import com.goplay.home.databinding.ItemPosterBinding

class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun inflate(parent: ViewGroup): PosterViewHolder {
            return PosterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_poster, parent, false)
            )

        }
    }

    private val binding = ItemPosterBinding.bind(itemView)

    fun bind(movie: Movie) {
        binding.posterUrl = if (!movie.posterPath.isNullOrEmpty()) {
            movie.posterPath
        } else ""

    }
}