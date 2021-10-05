package com.example.ibtechbootcamphmfive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ibtechbootcamphmfive.data.detail.Genre
import com.example.ibtechbootcamphmfive.databinding.MovieDetailCategoryRecyclerItemBinding

class MovieDetailCategoryAdapter() :
    RecyclerView.Adapter<MovieDetailCategoryAdapter.CategoryViewHolder>() {
    private var categories: List<Genre> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val binding = MovieDetailCategoryRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.init(category)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<Genre>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun getItemCount() = categories.size

    inner class CategoryViewHolder(private val binding: MovieDetailCategoryRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun init(category: Genre) {
            binding.category = category
        }
    }
}


