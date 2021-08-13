package com.goplay.core.network.service

import Constants.API_MOVIES
import Constants.API_TV
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
    @GET("$API_MOVIES/{movie_type}")
    suspend fun getMovies(
        @Path("movie_type") movieType: String
    ): Response<MovieResponse>

    @GET("$API_TV/{tv_type}")
    suspend fun getTvShow(
        @Path("tv_type") tvType: String
    ): Response<MovieResponse>

    @GET("{movie_type}/{movie_id}")
    suspend fun getDetail(
        @Path("movie_type") movieType: String,
        @Path("movie_id") movieId: Int
    ): Response<Movie>

    @GET("$PERSON/{type}")
    suspend fun getPerson(
        @Path("type") type: String
    ): Response<PeopleResponse>

    @GET("$PERSON/{person_id}")
    suspend fun getDetailPerson(
        @Path("person_id") personId: Int
    ): Response<PeopleResponse>

    @GET("$SEARCH/{type}")
    suspend fun <T> search(
        @Path("type") type: String,
        @Query("query") query: String
    ): T
}