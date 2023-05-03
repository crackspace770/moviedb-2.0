package com.fajar.moviedb.ui.search

import androidx.lifecycle.*
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel(){

    private val searchQuery = MutableLiveData<String>()

    fun setSearchQuery(query: String) {
        this.searchQuery.value = query
    }

    var searchResult: LiveData<Resource<List<Movie>>> =
        Transformations.switchMap(searchQuery) { query ->
            movieUseCase.getSearchMovie(query).asLiveData()
        }
}