package com.fajar.moviedb.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvDetailResponse (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("tagline")
    val tagline: String?,

    @field:SerializedName("genres")
    val genres: List<GenreItem>,

    @field:SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("popularity")
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,
        )