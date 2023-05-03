package com.fajar.moviedb.setting

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.moviedb.setting.movie.FavoriteMovieFragment
import com.fajar.moviedb.setting.tv.FavoriteTvFragment


class SectionPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvFragment()
        }
        return fragment as Fragment
    }
}