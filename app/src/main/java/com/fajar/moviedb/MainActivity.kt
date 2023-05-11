package com.fajar.moviedb


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.moviedb.databinding.ActivityMainBinding
import com.fajar.moviedb.ui.movie.MovieFragment
import com.fajar.moviedb.ui.main.MainFragment
import com.fajar.moviedb.ui.search.SearchFragment
import com.fajar.moviedb.ui.tv.TvFragment
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            setupBottomNavMenu(navController)
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        setFragment(MainFragment())
        val bottomNav = binding.navView
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_main -> setFragment(MainFragment())
                R.id.navigation_home -> setFragment(MovieFragment())
                R.id.navigation_tv-> setFragment(TvFragment())
                R.id.navigation_search-> setFragment(SearchFragment())
                R.id.navigation_favorite -> setFragment(supportFragmentManager.instantiate("com.fajar.moviedb.setting.FavoriteFragment"))
                else -> setFragment(MovieFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun FragmentManager.instantiate(className: String): Fragment {
        return fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), className)
    }

    @SuppressLint("CommitTransaction")
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}