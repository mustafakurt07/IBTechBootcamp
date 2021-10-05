package com.example.ibtechbootcamphmfive.ui

import androidx.lifecycle.ViewModelProvider
import com.example.ibtechbootcamphmfive.R
import com.example.ibtechbootcamphmfive.adapter.MovieDetailCategoryAdapter
import com.example.ibtechbootcamphmfive.base.BaseFragment
import com.example.ibtechbootcamphmfive.databinding.FragmentMovieDetailBinding
import com.example.ibtechbootcamphmfive.util.gone
import com.example.ibtechbootcamphmfive.util.toastLong
import com.example.ibtechbootcamphmfive.util.toastShort
import com.example.ibtechbootcamphmfive.util.visible
import com.example.ibtechbootcamphmfive.viewmodel.MovieDetailViewModel

class MovieDetail : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {
    override var viewModel: MovieDetailViewModel? = null

    private var adapter: MovieDetailCategoryAdapter = MovieDetailCategoryAdapter()

    override fun getLayoutID() = R.layout.fragment_movie_detail

    override fun observeLiveData() {

        viewModel?.details?.observe(this, {
            dataBinding.movie = it
            dataBinding.executePendingBindings()
            adapter.setCategories(it.getDetail().genres)

            initView()
        })
    }

    override fun networkConnection(): Boolean {
        return super.networkConnection()
    }

    private fun initView() {

        viewModel?.isInFavourite?.observe(this, {
            if (it) {
                dataBinding.addFavouriteImageView.setImageResource(R.drawable.ic_full_heart)
                dataBinding.removeFavouriteButton.visible()
            }
        })
    }

    override fun prepareView() {

        if (!networkConnection()) {
            dataBinding.noConnectionImageView.visible()
            dataBinding.noConnectionTextView.visible()
            dataBinding.detailScreenParentLayout.gone()
        }
        dataBinding.detailCategoryRecyclerView.adapter = adapter

        onClickListeners()
    }

    private fun onClickListeners() {
        dataBinding.removeFavouriteButton.setOnClickListener {
            removeFavourite()
        }
        dataBinding.addFavouriteImageView.setOnClickListener {
            addFavourite()
        }
    }


    private fun removeFavourite() {
        viewModel?.isInFavourite?.observe(this, {
            if (it) {
                viewModel?.deleteFavourite()
                toastLong("Movie is successfully removed from favourites")
                dataBinding.addFavouriteImageView.setImageResource(R.drawable.ic_empty_heart)
                dataBinding.removeFavouriteButton.gone()
            }

        })
    }

    private fun addFavourite() {
        viewModel?.isInFavourite?.observe(this, {
            if (it) {
                toastLong("This movie is already in favourite")
            } else {
                viewModel?.addFavourites()
                toastShort("Successfully added in favourites")
                dataBinding.addFavouriteImageView.setImageResource(R.drawable.ic_full_heart)
                dataBinding.addFavouriteImageView.isClickable = false
                dataBinding.removeFavouriteButton.visible()
            }
        })
    }


    override fun prepareViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory {
                MovieDetailViewModel(
                    requireContext(),
                    arguments?.getInt("movieId")!!
                )
            }
        ).get(MovieDetailViewModel::class.java)
    }

    override fun shouldCheckInternetConnection() = true

}