package com.fajar.moviedb.core.data.remote.network

import com.fajar.moviedb.core.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): ListMovieResponse

    @GET("search/multi")
   suspend fun getSearchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean
    ): MultiResponse

    @GET("tv/top_rated")
    suspend fun getPopularTv(
        @Query("api_key") apiKey: String
    ): ListTvResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovieList(
        @Query("api_key") apiKey: String
    ): ListMovieResponse


    @GET("trending/all/week")
    suspend fun getTrendingThisWeekList(
        @Query("api_key") apiKey: String
    ): MultiResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String
    ): TvDetailResponse


}