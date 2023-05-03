package com.fajar.moviedb.setting.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.moviedb.core.ui.MovieAdapter
import com.fajar.moviedb.di.FavoriteModuleDependencies
import com.fajar.moviedb.setting.DaggerFavoriteComponent
import com.fajar.moviedb.setting.FavoriteViewModel
import com.fajar.moviedb.setting.ViewModelFactory
import com.fajar.moviedb.setting.databinding.FragmentTvFavoriteBinding
import com.fajar.moviedb.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvFragment: Fragment(com.fajar.moviedb.setting.R.layout.fragment_tv_favorite)  {

    private val binding: FragmentTvFavoriteBinding by viewBinding()
    private val tvAdapter = MovieAdapter()

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }



    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    activity?.applicationContext as Context,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            tvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_FILM, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.getFavoriteTv().observe(viewLifecycleOwner) { dataTourism ->
                tvAdapter.setData(dataTourism)
             //   binding.viewEmptyFavorite.root.visibility =
                    if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvTvFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }

    }



}