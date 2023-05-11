package com.fajar.moviedb.core.di


import com.fajar.moviedb.core.data.remote.network.ApiService
import com.fajar.moviedb.core.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient{
        val hostname = Constant.HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, Constant.CERTIFICATE_PIN_1)
            .add(hostname, Constant.CERTIFICATE_PIN_2)
            .add(hostname, Constant.CERTIFICATE_PIN_3)
            .build()
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }
}