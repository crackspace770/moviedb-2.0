package com.fajar.moviedb.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fajar.moviedb.R
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.utils.Constant.Companion.IMAGE_BASE_URL
import com.fajar.moviedb.databinding.ActivityDetailsBinding
import com.fajar.moviedb.ui.movie.MovieViewModel
import com.fajar.moviedb.ui.tv.TvViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailActivity: AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
    }

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding
    private var isFavorite: Boolean = false
    private lateinit var movieTitle: String
    private val tvModel: TvViewModel by viewModels()
    private val movieModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        //(application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val film = intent.getParcelableExtra<Movie>(EXTRA_FILM)
        if (film != null){
            if (film.isTvShow) {
                getDetailTvShow(film)
            } else {
                getDetailMovie(film)

            }
        }

        binding.apply {

            btnShareDetail.setOnClickListener {
                val shareIntent = Intent()
                val appName = getString(R.string.app_name)
                val githubLink = getString(R.string.github_page)
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Watch $movieTitle on $appName \n" +
                        "$githubLink ")

                shareIntent.type = "text/plain"
                startActivity(Intent.createChooser(shareIntent, "Share To:"))
            }

        }

    }

    private fun getDetailMovie(movie: Movie?) {
        binding.apply {
            movie?.let { it ->
                isFavorite = it.isFavorite
                movieTitle = it.title ?: getString(R.string.no_data)
                showDetailMovie(it)
            }
             progressBar.visibility = View.VISIBLE
              viewError.root.visibility = View.GONE
            movie?.let { movieModel.setSelectedMovie(it) }
            movieModel.movieDetail.observe(this@DetailActivity) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            movie.data?.let {
                                showDetailMovie(it)
                            }
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                   //         viewError.root.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun getDetailTvShow(tvShow: Movie?) {
        binding.apply {
            tvShow?.let { it ->
                isFavorite = it.isFavorite
                movieTitle = it.title ?: getString(R.string.no_data)
                showDetailMovie(it)
            }
            progressBar.visibility = View.VISIBLE
            viewError.root.visibility = View.GONE
            tvShow?.let { tvModel.setSelectedTv(it) }
            tvModel.tvDetail.observe(this@DetailActivity) { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            tvShow.data?.let {
                                showDetailMovie(it)
                            }
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            viewError.root.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }


    private fun showDetailMovie(movie: Movie?) {
        binding.apply {
            movie?.apply {
                supportActionBar?.title = title
                tvTitle.text= title
                tvDescription.text = overview
                tvGenre.text=  if (genres == null || genres == "") getString(R.string.no_genre_data) else genres
                rateStar.rating = movie.voteAverage.toFloat() / 2
                tvVoteCount.text= popularity.toString()
                ivDetailImage.loadImage("${IMAGE_BASE_URL}${backdropPath}")
               // ivPoster.loadImage("${IMAGE_BASE_URL}${posterPath}")


                if (releaseDate == null || releaseDate == "") {
                    tvRelease.text = getString(R.string.no_data)
                } else {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val date = releaseDate.let { dateFormat.parse(it) }
                    if (date != null) {
                        val dateFormatted =
                            SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)
                        tvRelease.text = dateFormatted
                    } else {
                        tvRelease.text = getString(R.string.no_data)
                    }
                }

                val category = if (isTvShow) "TV SHOW" else "MOVIE"


                tvRuntime.text = getString(R.string.category, category)
                if (runtime != null) {
                    if (category == "MOVIE") {
                        val hours = runtime!!.div(60)
                        val minutes = runtime!!.rem(60)
                        val duration = "${hours}h ${minutes}m"
                       tvRuntime.text = duration
                    } else {
                        tvRuntime.text = getString(R.string.episodeRuntime, runtime)
                    }
                } else {
                    tvRuntime.text = getString(R.string.no_data)
                }

                setFavorite(movie)

            }
        }

    }

    private fun setFavorite(movie: Movie){

        binding.apply {
            var statusFavorite = isFavorite

            buttonStatusFavorite(statusFavorite)

            btnFavoriteDetail.setOnClickListener {
                isFavorite = !isFavorite
                if (isFavorite) {
                    if (movie.isTvShow)
                        detailViewModel.setFavoriteMovie(movie, isFavorite
                        ) else detailViewModel.setFavoriteTv(movie, isFavorite)
                    statusFavorite = !statusFavorite
                    detailViewModel.setFavoriteMovie(movie, statusFavorite)
                    detailViewModel.setFavoriteTv(movie, statusFavorite)
                    buttonStatusFavorite(statusFavorite)
                }
            }

        }

    }


    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .error(R.drawable.ic_error_image)
            .into(this)
    }

    private fun buttonStatusFavorite(statusFavorite: Boolean) {

        binding.btnFavoriteDetail.apply {
            if (statusFavorite) {
                // Set background color to gray
                setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
                // Set text to 'Added to favorite'
                text = "Added to favorite"
            } else {
                // Reset background color to the default color
                setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                // Reset text to 'Add Favorite'
                text = "Add Favorite"
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }



}