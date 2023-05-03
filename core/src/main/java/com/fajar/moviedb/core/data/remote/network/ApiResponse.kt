package com.fajar.moviedb.core.data.remote.network

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    data class Empty<out T> (val data: T) : ApiResponse<T>()
}