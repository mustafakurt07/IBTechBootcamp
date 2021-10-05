package com.example.ibtechbootcamphmfive.repo

import androidx.lifecycle.MutableLiveData
import com.example.ibtechbootcamphmfive.base.BaseCallBack
import com.example.ibtechbootcamphmfive.data.detail.MovieDetails
import com.example.ibtechbootcamphmfive.service.ServiceConnector
import com.example.ibtechbootcamphmfive.util.Constants.LANGUAGE

class MovieDetailRepo {
    val onDetailFetched = MutableLiveData<MovieDetails>()
    fun getMovieDetailById(movie_id: Int) {
        ServiceConnector.restInterface.getMovieDetailById(movie_id, LANGUAGE)
            .enqueue(object : BaseCallBack<MovieDetails>() {
                override fun onSuccess(data: MovieDetails) {
                    onDetailFetched.postValue(data)
                }
            })
    }
}