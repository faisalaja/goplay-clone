package com.goplay.home.data

import com.goplay.core.network.data.model.result.Movie

data class Categories(
    val title: String? = "",
    val description: String? = "",
    val uri: String? = "",
    val movies: List<Movie>? = emptyList(),
)