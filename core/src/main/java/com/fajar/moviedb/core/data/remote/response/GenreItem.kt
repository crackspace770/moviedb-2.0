package com.fajar.moviedb.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenreItem (
        @field:SerializedName("id")
        val id: Int,
        @field:SerializedName("name")
        val name: String
        )