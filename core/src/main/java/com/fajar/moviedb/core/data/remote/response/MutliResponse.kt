package com.fajar.moviedb.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MultiResponse(
    @field:SerializedName("results")
    val results: List<MultiResultsItem>,
)

data class MultiResultsItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String?,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("first_air_date")
    val firstAirDate: String?,

    @field:SerializedName("media_type")
    val mediaType: String,

)
