package com.goplay.core.network.data.repository

import Constants.MOVIE
import Constants.TV
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.data.model.result.People
import com.goplay.core.network.data.source.PagingMovieDataSource
import com.goplay.core.network.data.source.PagingPeopleDataSource
import com.goplay.core.network.service.ApiServices
import com.goplay.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val services: ApiServices) : Repository {

    override suspend fun fetchMovies(type: String): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(10, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingMovieDataSource(
                    services = services,
                    api_type = MOVIE,
                    type = type
                )
            }).flow
    }

    override suspend fun fetchTvShow(type: String): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(10, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingMovieDataSource(
                    services = services,
                    api_type = TV,
                    type = type
                )
            }).flow
    }

    override suspend fun fetchDetailMovie(
        movieType: String,
        movieId: Int
    ): LiveData<Resource<Movie>> {
        val request = services.getDetail(movieType, movieId)
        val result: MutableLiveData<Resource<Movie>> = MutableLiveData()
        result.postValue(Resource.loading(null))
        try {
            if (request.isSuccessful) {
                val responseData = request.body()
                result.postValue(Resource.success(responseData, null))
            } else result.postValue(Resource.error(request.errorBody().toString(), null))

        } catch (e: IOException) {
            result.postValue(Resource.error(e.message, null))
        } catch (e: HttpException) {
            result.postValue(Resource.error(e.message, null))
        }
        return result
    }

    override suspend fun fetchPeople(type: String): Flow<PagingData<People>> {
        return Pager(config = PagingConfig(10, enablePlaceholders = false),
            pagingSourceFactory = {
                PagingPeopleDataSource(
                    services = services,
                    type = type
                )
            }).flow
    }

    override suspend fun fetchDetailPeople(): LiveData<Resource<People>> {
        TODO("Not yet implemented")
    }

    private fun <T> handleRequestMovie(request: Response<T>): Flow<Resource<T>> {
        return flow {
            emit(Resource.loading(null))
            try {
                if (request.isSuccessful) {
                    val responseData = request.body()
                    val url = request.raw().request.url.toString()
                    emit(Resource.success(responseData, url))
                } else emit(Resource.error(request.errorBody().toString(), null))

            } catch (e: IOException) {
                emit(Resource.error(e.message, null))
            } catch (e: HttpException) {
                emit(Resource.error(e.message, null))
            }
        }.flowOn(Dispatchers.IO)
    }
}