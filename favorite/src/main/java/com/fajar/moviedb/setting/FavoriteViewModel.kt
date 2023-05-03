package com.fajar.moviedb.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase

class FavoriteViewModel (private val movieUseCase: MovieUseCase): ViewModel() {


    fun getFavoriteMovie(): LiveData<List<Movie>> {
        return movieUseCase.getFavoriteMovie().asLiveData()
    }

    fun getFavoriteTv(): LiveData<List<Movie>> {
        return movieUseCase.getFavoriteTv().asLiveData()
    }


}