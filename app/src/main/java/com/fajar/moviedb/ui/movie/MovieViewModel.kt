package com.fajar.moviedb.ui.movie


import androidx.lifecycle.*
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val selectedMovie = MutableLiveData<Movie>()

    fun setSelectedMovie(movie: Movie) {
        this.selectedMovie.value = movie
    }

    var movieDetail: LiveData<Resource<Movie>> =
        Transformations.switchMap(selectedMovie) { movie ->
            movieUseCase.getMovieDetail(movie).asLiveData()
        }

    fun getPopularMoviesList(sort: String, shouldFetchAgain: Boolean): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getTrendingMovie(sort, shouldFetchAgain).asLiveData()
    }
}