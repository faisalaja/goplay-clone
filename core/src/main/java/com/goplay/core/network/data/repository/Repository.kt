package com.goplay.core.network.data.repository

import androidx.lifecycle.LiveData
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.data.model.result.MovieResponse
import com.goplay.core.network.data.model.result.People
import com.goplay.core.network.data.model.result.PeopleResponse
import com.goplay.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchMovies(type: String): Flow<Resource<MovieResponse>>
    suspend fun fetchTvShow(type: String): Flow<Resource<MovieResponse>>
    suspend fun fetchDetailMovie(movieType:String, movieId: Int): LiveData<Resource<Movie>>
    suspend fun fetchPeople(type: String): Flow<Resource<PeopleResponse>>
    suspend fun fetchDetailPeople(): LiveData<Resource<People>>
}