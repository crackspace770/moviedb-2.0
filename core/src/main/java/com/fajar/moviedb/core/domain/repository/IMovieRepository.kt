package com.fajar.moviedb.core.domain.repository

import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {


    fun getTrendingMovie(sort: String, shouldFetchAgain: Boolean): Flow<Resource<List<Movie>>>
    fun getTrendingTv(sort: String, shouldFetchAgain: Boolean): Flow<Resource<List<Movie>>>
    fun getTrendingThisWeekList(): Flow<Resource<List<Movie>>>

    fun getPopularMovie(): Flow<Resource<List<Movie>>>
    fun getPopularTv():Flow<Resource<List<Movie>>>

    fun getSearchMovie(query: String) : Flow<Resource<List<Movie>>>

    fun getMovieDetail(movie: Movie): Flow<Resource<Movie>>
    fun getTvDetail(tv: Movie): Flow<Resource<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteTv(): Flow<List<Movie>>

    suspend fun insertFavoriteItem(item: Movie, state: Boolean)


}