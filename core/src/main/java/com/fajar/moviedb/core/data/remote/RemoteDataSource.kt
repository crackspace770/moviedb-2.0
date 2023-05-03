package com.fajar.moviedb.core.data.remote

import android.util.Log
import com.fajar.moviedb.core.data.remote.network.ApiResponse

import com.fajar.moviedb.core.data.remote.network.ApiService
import com.fajar.moviedb.core.data.remote.response.*
import com.fajar.moviedb.core.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    private val apiKey = Constant.API_KEY

    suspend fun getPopularMovie(): Flow<ApiResponse<ListMovieResponse>> {
        return flow {
            try {
                val response = apiService.getPopularMovie(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Popular Movie List")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularTv(): Flow<ApiResponse<ListTvResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularTv(apiKey)
                val tvList = response.results
                if (tvList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Popular TvShow List")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(): Flow<ApiResponse<ListMovieResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getUpcomingMovieList(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Popular Movie List")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTv(): Flow<ApiResponse<ListTvResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPopularTv(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Popular Movie List")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



    suspend fun getTrendingThisWeekList(): Flow<ApiResponse<MultiResponse>> {
        return flow {
            try {
                val response = apiService.getTrendingThisWeekList(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Trending Movie List")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun searchMovie(query:String): Flow<ApiResponse<MultiResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getSearchMovie(apiKey, query, false)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Search Movie Results")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId, apiKey)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Movie Detail")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getTvDetail(tvShowId: Int): Flow<ApiResponse<TvDetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailTvShow(tvShowId, apiKey)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Tv Show Detail")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}