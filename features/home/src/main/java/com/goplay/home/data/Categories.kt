package com.goplay.home.data

import androidx.paging.PagingData
import com.goplay.core.network.data.model.result.Movie
import kotlinx.coroutines.flow.Flow

data class Categories(
    var title: String? = "",
    var description: String? = "",
    var uri: String? = "",
    var pagingFlow: Flow<PagingData<Movie>> ?= null
)