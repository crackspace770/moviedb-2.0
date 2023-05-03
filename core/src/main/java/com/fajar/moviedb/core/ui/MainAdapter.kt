package com.fajar.moviedb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajar.moviedb.core.databinding.ItemListBinding
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.utils.Constant.Companion.IMAGE_BASE_URL
import com.fajar.moviedb.core.utils.MovieDiffUtil
import java.util.ArrayList

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null


    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.apply {
                tvItemSubtitle.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("${IMAGE_BASE_URL}${movie.posterPath}")
                    .into(ivPoster)

                tvItemTitle.text = movie.title
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsBinding)
    }

    override fun getItemCount(): Int = if (listData.size <= 10) listData.size else 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    fun setData(newMovieList: List<Movie>){
        val diffUtil = MovieDiffUtil(listData,newMovieList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.listData.clear()
        this.listData.addAll(newMovieList)
        diffResults.dispatchUpdatesTo(this)
    }

}