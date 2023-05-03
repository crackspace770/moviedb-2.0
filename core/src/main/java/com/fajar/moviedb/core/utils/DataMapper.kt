package com.fajar.moviedb.core.utils


import com.fajar.moviedb.core.data.local.entity.MovieEntity
import com.fajar.moviedb.core.data.remote.response.*

import com.fajar.moviedb.core.domain.model.Movie

object DataMapper {


    //  Movie Response To Entity
    fun mapResponsesToEntities(input: ListMovieResponse): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.results.forEach {
            val movie = MovieEntity(
                 it.id,
                it.title,
                it.releaseDate,
               it.overview,
                it.genres,
                it.runtime,
                it.tagline,
                 it.voteAverage,
               it.popularity,
               it.posterPath,
                 it.backdropPath,

            )
            movieList.add(movie)
        }
        return movieList
    }


    //  TV Response To Entity
    fun mapTvResponsesToEntities(data: ListTvResponse): List<MovieEntity> {
        val tvShowList = ArrayList<MovieEntity>()
        data.results.forEach {
            val tvShow = MovieEntity(
                it.id,
                it.name,
                it.firstAirDate,
                it.overview,
                null,
                null,
                null,
                it.voteAverage,
                it.popularity,
                it.posterPath,
                it.backdropPath,
                isTvShow = true
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvToEntities(input: ListTvResponse): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.results.forEach {
            val movie = MovieEntity(
                it.id,
                it.name,
                it.firstAirDate,
                it.overview,
                it.genres,
                it.runtime,
                it.tagline,
                it.voteAverage,
                it.popularity,
                it.posterPath,
                it.backdropPath,

                )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapDetailMovieResponseToDomain(data: MovieDetailResponse): Movie {
        data.apply {
            val genreList = ArrayList<String>()
            genres.forEach { genreList.add(it.name) }
            val genre = genreList.joinToString(separator = ", ")
            return Movie(
                id,
                title,
                releaseDate,
                overview,
                tagline,
                genre,
                runtime,
                voteAverage,
                popularity,
                posterPath,
                backdropPath,
            )
        }
    }

    fun mapDetailTvResponseToDomain(data: TvDetailResponse): Movie {
        data.apply {
            val genreList = ArrayList<String>()
            genres.forEach { genreList.add(it.name) }
            val genre = genreList.joinToString(separator = ", ")
            return Movie(
                id,
                name,
                firstAirDate,
                overview,
                tagline,
                genre,
                if (episodeRunTime.isEmpty()) 0 else episodeRunTime[0],
                voteAverage,
                popularity,
                posterPath,
                backdropPath,
                isTvShow = true
            )
        }
    }


    fun mapEntitiesToDomain(data: List<MovieEntity>): List<Movie> {
        return data.map {
            mapEntityToDomain(it)
        }
    }


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        releaseDate = input.releaseDate,
        overview = input.overview,
        genres = input.genres,
        runtime = input.runtime,
        tagline = input.tagline,
        voteAverage = input.voteAverage,
        popularity = input.popularity,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        isFavorite = input.isFavorite,
        isTvShow = input.isTvShow
    )

    private fun mapEntityToDomain(data: MovieEntity): Movie{
        return Movie(
            id = data.id,
            title = data.title,
            releaseDate = data.releaseDate,
            overview = data.overview,
            tagline = data.tagline,
            genres = data.genres,
            runtime = data.runtime,
            voteAverage = data.voteAverage,
            popularity = data.popularity,
            posterPath = data.posterPath,
            backdropPath = data.backdropPath,
            isFavorite = data.isFavorite,
            isTvShow = data.isTvShow
        )
    }


    fun mapMultiResponsesToDomain(data: MultiResponse): List<Movie> {
        return data.results.map {
            Movie(
                id = it.id,
                title = if(it.mediaType == "tv") it.name else it.title,
                releaseDate = if(it.mediaType == "tv") it.firstAirDate else it.releaseDate,
                isTvShow = it.mediaType == "tv",
                overview = it.overview,
                tagline = null,
                genres = null,
                runtime = null,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
            )
        }
    }


}