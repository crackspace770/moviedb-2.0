package com.fajar.moviedb.setting

import android.content.Context
import com.fajar.moviedb.di.FavoriteModuleDependencies
import com.fajar.moviedb.setting.movie.FavoriteMovieFragment
import com.fajar.moviedb.setting.tv.FavoriteTvFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}