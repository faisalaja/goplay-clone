package com.goplay.home.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.People
import com.goplay.home.R
import com.goplay.home.databinding.ItemRectangleRoundedBinding

class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun inflate(parent: ViewGroup): PeopleViewHolder {
            return PeopleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_rectangle_rounded, parent, false
                )
            )
        }
    }

    private val bindingItem = ItemRectangleRoundedBinding.bind(itemView)

    fun bind(people: People) {
        bindingItem.urlImages = if (!people.profilePath.isNullOrEmpty()) {
            people.profilePath
        } else ""

        bindingItem.title = people.name.substringBefore(" ")
    }
}