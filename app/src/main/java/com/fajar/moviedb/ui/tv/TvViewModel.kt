package com.fajar.moviedb.ui.tv

import androidx.lifecycle.*
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val selectedMovie = MutableLiveData<Movie>()

    fun setSelectedTv(movie: Movie) {
        this.selectedMovie.value = movie
    }


    var tvDetail: LiveData<Resource<Movie>> =
        Transformations.switchMap(selectedMovie) { movie ->
            movieUseCase.getTvDetail(movie).asLiveData()
        }

    fun getPopularTvList(sort: String, shouldFetchAgain: Boolean): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getTrendingTv(sort, shouldFetchAgain).asLiveData()
    }



}