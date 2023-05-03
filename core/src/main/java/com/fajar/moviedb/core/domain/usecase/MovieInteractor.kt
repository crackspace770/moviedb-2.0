package com.fajar.moviedb.core.domain.usecase

import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor (private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getTrendingMovie(sort: String, shouldFetchAgain: Boolean) = movieRepository
        .getTrendingMovie(sort,shouldFetchAgain)

    override fun getTrendingTv(sort: String, shouldFetchAgain: Boolean) = movieRepository
        .getTrendingTv(sort,shouldFetchAgain)

    override fun getTrendingThisWeekList() = movieRepository.getTrendingThisWeekList()

    override fun getPopularMovie() = movieRepository.getPopularMovie()
    override fun getPopularTv() = movieRepository.getPopularTv()
    
    override fun getSearchMovie(query: String) = movieRepository.getSearchMovie(query)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
    override fun getFavoriteTv() = movieRepository.getFavoriteTv()

    override fun getMovieDetail(movie: Movie) = movieRepository.getMovieDetail(movie)
    override fun getTvDetail(tv: Movie) = movieRepository.getTvDetail(tv)

    override suspend fun insertFavoriteItem(item: Movie, state: Boolean) =
        movieRepository.insertFavoriteItem(item, state)

}