package com.example.ibtechbootcamphmfive.ui

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphmfive.R
import com.example.ibtechbootcamphmfive.adapter.FavouritesMovieListAdapter
import com.example.ibtechbootcamphmfive.base.BaseFragment
import com.example.ibtechbootcamphmfive.base.BaseRecyclerItemClickListener
import com.example.ibtechbootcamphmfive.data.MovieRoom
import com.example.ibtechbootcamphmfive.databinding.FragmentFavouritesBinding
import com.example.ibtechbootcamphmfive.util.gone
import com.example.ibtechbootcamphmfive.util.visible
import com.example.ibtechbootcamphmfive.viewmodel.FavoriteViewModel

class FavoriteFragment: BaseFragment<FavoriteViewModel, FragmentFavouritesBinding>() {

    override var viewModel: FavoriteViewModel? = null

    private var adapter: FavouritesMovieListAdapter? = null
    private var favouriteList: ArrayList<MovieRoom> = arrayListOf()

    override fun getLayoutID() = R.layout.fragment_favourites

    override fun observeLiveData() {

    }

    override fun prepareView() {
        viewModel?.getAllFavourites(requireContext())
        favouriteList = viewModel?.allFavourites as ArrayList<MovieRoom>

        if (favouriteList.size == 0) {
            dataBinding.emptyFavouriteText.visible()
            dataBinding.FavouriteMoviesRecyclerView.gone()
        }

        adapter = FavouritesMovieListAdapter(favouriteList,
            object : BaseRecyclerItemClickListener<MovieRoom> {
                override fun onItemClicked(clickedObject: MovieRoom, id: Int) {
                    val bundle = bundleOf("movieId" to clickedObject.secondaryId)
                    findNavController().navigate(
                        R.id.action_tabLayoutControllerFragment_to_movieDetailFragment, bundle
                    )
                }
            })
        dataBinding.FavouriteMoviesRecyclerView.adapter = adapter

    }

    override fun prepareViewModel() {
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }
}