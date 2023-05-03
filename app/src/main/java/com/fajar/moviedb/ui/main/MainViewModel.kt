package com.fajar.moviedb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getTrendingThisWeekList(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getTrendingThisWeekList().asLiveData()
    }

    fun getUpcomingMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getPopularMovie().asLiveData()
    }

    fun getTopTvShowList(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getPopularTv().asLiveData()
    }


}