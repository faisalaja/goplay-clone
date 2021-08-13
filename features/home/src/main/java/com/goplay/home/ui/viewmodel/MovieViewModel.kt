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
import com.goplay.core.network.data.model.result.MovieResponse
import com.goplay.core.network.data.repository.RepositoryImpl
import com.goplay.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {
    private val movieCollection: MutableLiveData<MutableList<Flow<Resource<MovieResponse>>>> =
        MutableLiveData()

    private val tvShowCollection: MutableLiveData<MutableList<Flow<Resource<MovieResponse>>>> =
        MutableLiveData()

    val movies: LiveData<MutableList<Flow<Resource<MovieResponse>>>>
        get() = movieCollection

    val tvShow: LiveData<MutableList<Flow<Resource<MovieResponse>>>>
        get() = tvShowCollection

    init {
        getMovie()
        getTvShow()
    }

    private fun getMovie() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchMovies(NOW_PLAYING).let {
                    movieCollection.postValue(mutableListOf(it))
                }
                repositoryImpl.fetchMovies(TOP_RATED).let {
                    movieCollection.postValue(mutableListOf(it))
                }
                repositoryImpl.fetchMovies(UPCOMING).let {
                    movieCollection.postValue(mutableListOf(it))
                }
                repositoryImpl.fetchMovies(POPULAR).let {
                    movieCollection.postValue(mutableListOf(it))
                }
            }
        }
    }

    private fun getTvShow() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchTvShow(TvShowType.AIRING_TODAY).let {
                    tvShowCollection.postValue(mutableListOf(it))
                }
            }
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchTvShow(TvShowType.ON_THE_AIR).let {
                    tvShowCollection.postValue(mutableListOf(it))
                }
            }
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchTvShow(TvShowType.TOP_RATED).let {
                    tvShowCollection.postValue(mutableListOf(it))
                }
            }
            withContext(Dispatchers.Main) {
                repositoryImpl.fetchTvShow(TvShowType.POPULAR).let {
                    tvShowCollection.postValue(mutableListOf(it))
                }
            }
        }
    }

}