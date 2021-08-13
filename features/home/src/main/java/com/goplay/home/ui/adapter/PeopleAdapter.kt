package com.goplay.home.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goplay.core.network.data.model.result.People
import com.goplay.home.ui.holder.PeopleViewHolder

class PeopleAdapter : RecyclerView.Adapter<PeopleViewHolder>() {
    var people: List<People>? = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        return PeopleViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        people?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount() = people?.size ?: 0
}