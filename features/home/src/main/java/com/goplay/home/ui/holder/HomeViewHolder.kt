package com.goplay.home.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goplay.home.R
import com.goplay.home.data.Categories
import com.goplay.home.databinding.ItemCategoriesBinding

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun inflate(parent: ViewGroup): HomeViewHolder {
            return HomeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
            )

        }
    }

    val binding = ItemCategoriesBinding.bind(itemView)

    fun bind(data: Categories?) {
        binding.categoryTitle = data?.title.orEmpty()
    }
}