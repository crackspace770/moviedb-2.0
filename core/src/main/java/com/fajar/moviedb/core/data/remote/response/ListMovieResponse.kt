package com.fajar.moviedb.core.data.remote.response


import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class ListMovieResponse (

    @field:SerializedName("results")
    val results: List<MovieItem>,
)

data class MovieItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("tagline")
    val tagline: String,

    @field:SerializedName("genre")
    val genres: String,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("is_tv_show")
    val isTvShow: Boolean,

    )

