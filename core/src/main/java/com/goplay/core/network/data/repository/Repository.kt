package com.goplay.core.network.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.data.model.result.People
import com.goplay.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchMovies(type: String): Flow<PagingData<Movie>>
    suspend fun fetchTvShow(type: String): Flow<PagingData<Movie>>
    suspend fun fetchDetailMovie(movieType: String, movieId: Int): LiveData<Resource<Movie>>
    suspend fun fetchPeople(type: String): Flow<PagingData<People>>
    suspend fun fetchDetailPeople(): LiveData<Resource<People>>
}