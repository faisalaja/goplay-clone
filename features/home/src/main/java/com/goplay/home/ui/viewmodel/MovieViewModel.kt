package com.goplay.home.ui.viewmodel

import MovieType.NOW_PLAYING
import MovieType.POPULAR
import MovieType.TOP_RATED
import MovieType.UPCOMING
import TvShowType
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.data.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {
    private val movieCollection: MutableLiveData<MutableList<Flow<PagingData<Movie>>>> =
        MutableLiveData()

    private val tvShowCollection: MutableLiveData<MutableList<Flow<PagingData<Movie>>>> =
        MutableLiveData()

    val movies: LiveData<MutableList<Flow<PagingData<Movie>>>>
        get() = movieCollection

    val tvShow: LiveData<MutableList<Flow<PagingData<Movie>>>>
        get() = tvShowCollection

    init {
        getMovie()
        getTvShow()
    }

    private fun getMovie() {
        viewModelScope.launch {
            val nowPlaying = repositoryImpl.fetchMovies(NOW_PLAYING).cachedIn(viewModelScope)
            val topRate = repositoryImpl.fetchMovies(TOP_RATED).cachedIn(viewModelScope)
            val upcoming = repositoryImpl.fetchMovies(UPCOMING).cachedIn(viewModelScope)
            val popular = repositoryImpl.fetchMovies(POPULAR).cachedIn(viewModelScope)

            val collections = mutableListOf(nowPlaying, topRate, upcoming, popular)
            movieCollection.postValue(collections)
        }
    }

    private fun getTvShow() {
        viewModelScope.launch {
            val airingToday = repositoryImpl.fetchTvShow(TvShowType.AIRING_TODAY)
                .cachedIn(viewModelScope)
            val onTheAir = repositoryImpl.fetchTvShow(TvShowType.ON_THE_AIR)
                .cachedIn(viewModelScope)
            val topRate = repositoryImpl.fetchTvShow(TvShowType.TOP_RATED).cachedIn(viewModelScope)
            val popular = repositoryImpl.fetchTvShow(TvShowType.POPULAR).cachedIn(viewModelScope)

            val collections = mutableListOf(airingToday, onTheAir, topRate, popular)
            tvShowCollection.postValue(collections)
        }
    }
}