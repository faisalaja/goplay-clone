package com.goplay.core.network.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.service.ApiServices
import retrofit2.HttpException
import java.io.IOException

class PagingMovieDataSource(
    private val services: ApiServices,
    private val api_type: String,
    private val type: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: FIRST_PAGE

        return try {
            val jsonResponse = services.getMovie(apiType = api_type, movieType = type, page = page)
            val movies = jsonResponse.body()?.movies ?: emptyList()
            val nextKey = if (movies.isEmpty()) null else page.plus(1)

            Log.d("TAG", "load: ${jsonResponse.raw().request.url}")
            LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}