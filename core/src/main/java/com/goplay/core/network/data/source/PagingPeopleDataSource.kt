package com.goplay.core.network.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.goplay.core.network.data.model.result.People
import com.goplay.core.network.service.ApiServices
import retrofit2.HttpException
import java.io.IOException

class PagingPeopleDataSource(
    private val services: ApiServices,
    private val type: String
) : PagingSource<Int, People>() {
    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val page = params.key ?: FIRST_PAGE

        return try {
            val jsonResponse = services.getPerson(type = type, page = page)
            val people = jsonResponse.body()?.people ?: emptyList()
            val nextKey = if (people.isEmpty()) null else page.plus(1)

            LoadResult.Page(
                data = people,
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