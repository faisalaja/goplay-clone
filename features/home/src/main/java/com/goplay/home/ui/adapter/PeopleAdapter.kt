package com.goplay.home.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.goplay.core.network.data.model.result.People
import com.goplay.home.ui.holder.PeopleViewHolder

class PeopleAdapter : PagingDataAdapter<People, PeopleViewHolder>(DiffUtilCallBack) {
    object DiffUtilCallBack : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder.inflate(parent)
    }
}