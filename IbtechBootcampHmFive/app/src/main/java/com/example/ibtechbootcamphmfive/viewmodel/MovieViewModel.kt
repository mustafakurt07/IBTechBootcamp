package com.example.ibtechbootcamphmfive.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.ibtechbootcamphmfive.repo.PopularMovieRepo
import com.example.ibtechbootcamphmfive.ui.FilmListViewStateModel

class MovieViewModel: ViewModel() {

    private var page = 1 //required for pagination
    val movies = MediatorLiveData<FilmListViewStateModel>()
    private val apiRepository = PopularMovieRepo()

    init {
        apiRepository.getAllMovies(page)
        movies.addSource(apiRepository.onMoviesFetched) {
            movies.value = FilmListViewStateModel(it)
        }
    }

    fun getMoviesWithPagination() {
        apiRepository.getAllMovies(++page)
    }

}