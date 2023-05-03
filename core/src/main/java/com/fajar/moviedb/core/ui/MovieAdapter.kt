package com.fajar.moviedb.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fajar.moviedb.core.R
import com.fajar.moviedb.core.databinding.ItemListNewBinding
import com.fajar.moviedb.core.domain.model.Movie
import com.fajar.moviedb.core.utils.Constant.Companion.IMAGE_BASE_URL
import com.fajar.moviedb.core.utils.MovieDiffUtil

import java.util.ArrayList

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null


    fun setData(newListData: List<Movie>) {
        val diffUtil = MovieDiffUtil(listData,newListData)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        this.listData.clear()
        this.listData.addAll(newListData)
        diffResults.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_new, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListNewBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${IMAGE_BASE_URL}${data.posterPath}")
                    .into(ivPoster)
                tvItemTitle.text = data.title

                if (data.releaseDate == null){
                    tvRelease.text = itemView.context.getString(R.string.no_data)
                } else {
                    val date: List<String> = data.releaseDate.split("-")
                    val year = date[0]
                    tvRelease.text = year
                }

                rateStar.rating = data.voteAverage.toFloat() / 2


            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    fun clearList(){
        val size = listData.size
        listData.clear()
        notifyItemRangeRemoved(0, size)
    }


}