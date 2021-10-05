package com.example.ibtechbootcamphmfive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ibtechbootcamphmfive.R
import com.example.ibtechbootcamphmfive.base.BaseRecyclerItemClickListener
import com.example.ibtechbootcamphmfive.data.MovieResults
import com.example.ibtechbootcamphmfive.databinding.MovieListRecyclerItemBinding

class MovieListAdapter(private val movieList: ArrayList<MovieResults>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemClickListener: BaseRecyclerItemClickListener<MovieResults>? = null

    constructor(
        movieList: ArrayList<MovieResults>,
        itemClickListener: BaseRecyclerItemClickListener<MovieResults>
    ) : this(movieList) {
        this.itemClickListener = itemClickListener
    }

    companion object {
        private const val MOVIE = 1
        private const val LOADING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MOVIE) {
            MovieViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.movie_list_recycler_item,
                    parent,
                    false
                )
            )
        } else {
            ProgressBarViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_progress,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = this.movieList[position]
        if (movieList[position].viewType == MOVIE) {
            holder as MovieViewHolder
            holder.binding(movie)
            holder.setOnClickListener(movie, this.itemClickListener!!)
        }
    }

    inner class MovieViewHolder(private val binding: MovieListRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(movie: MovieResults) {
            binding.movies = movie
            binding.executePendingBindings()
        }

        fun setOnClickListener(
            movie: MovieResults,
            itemClickListener: BaseRecyclerItemClickListener<MovieResults>
        ) {
            binding.root.setOnClickListener {
                itemClickListener.onItemClicked(movie, it.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    override fun getItemViewType(position: Int) = this.movieList[position].viewType

    inner class ProgressBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}