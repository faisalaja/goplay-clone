package com.goplay.core.network.service

import Constants.API_VERSION
import Constants.PERSON
import Constants.SEARCH
import com.goplay.core.network.data.model.result.Movie
import com.goplay.core.network.data.model.result.MovieResponse
import com.goplay.core.network.data.model.result.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("$API_VERSION/{api_type}/{movie_type}")
    suspend fun getMovie(
        @Path("api_type") apiType: String,
        @Path("movie_type") movieType: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>

    @GET("{movie_type}/{movie_id}")
    suspend fun getDetail(
        @Path("movie_type") movieType: String,
        @Path("movie_id") movieId: Int
    ): Response<Movie>

    @GET("$PERSON/{type}")
    suspend fun getPerson(
        @Path("type") type: String,
        @Query("page") page: Int,
    ): Response<PeopleResponse>

    @GET("$PERSON/{person_id}")
    suspend fun getDetailPerson(
        @Path("person_id") personId: Int,
    ): Response<PeopleResponse>

    @GET("$SEARCH/{type}")
    suspend fun <T> search(
        @Path("type") type: String,
        @Query("query") query: String
    ): T
}