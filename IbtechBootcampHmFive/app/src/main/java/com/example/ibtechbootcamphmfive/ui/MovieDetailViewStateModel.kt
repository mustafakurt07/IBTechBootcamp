package com.example.ibtechbootcamphmfive.ui

import com.example.ibtechbootcamphmfive.data.detail.MovieDetails


class MovieDetailViewStateModel(private val detail: MovieDetails) {

    fun getDetail(): MovieDetails = detail
    fun isAdult(): String {
        return if (detail.adult)
            "PG-13"
        else
            "PG-18"
    }
}