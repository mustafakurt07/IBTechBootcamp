package com.example.ibtechbootcamphmfive.ui

import com.example.ibtechbootcamphmfive.data.MovieResponse
import com.example.ibtechbootcamphmfive.data.MovieResults

 class FilmListViewStateModel(private val filmsResponse: MovieResponse) {
    fun getList(): List<MovieResults> = filmsResponse.movieResults
}