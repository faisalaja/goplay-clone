package com.goplay.core.network.data.model.result

import com.google.gson.annotations.SerializedName
import com.goplay.core.network.data.model.Dates

data class MovieResponse(
    @SerializedName("dates") val dates: Dates,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val movies: List<Movie>,
)

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_name") val originalName: String,
)