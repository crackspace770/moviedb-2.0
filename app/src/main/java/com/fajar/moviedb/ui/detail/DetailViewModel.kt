package com.fajar.moviedb.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(item: Movie, newState: Boolean) {
        viewModelScope.launch {
            movieUseCase.insertFavoriteItem(item, newState)
        }
    }

    fun setFavoriteTv(item: Movie, newState: Boolean){
        viewModelScope.launch {
            movieUseCase.insertFavoriteItem(item, newState)
        }
    }

}