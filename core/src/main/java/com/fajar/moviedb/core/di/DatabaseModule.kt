package com.fajar.moviedb.core.di

import android.content.Context
import androidx.room.Room
import com.fajar.moviedb.core.data.local.room.MovieDao
import com.fajar.moviedb.core.data.local.room.MovieDatabase
import com.fajar.moviedb.core.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(Constant.PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(app, MovieDatabase::class.java, Constant.DB_NAME)
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideTourismDao(database: MovieDatabase): MovieDao = database.movieDao()
}