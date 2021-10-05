package com.example.ibtechbootcamphmfive.repo

import androidx.lifecycle.MutableLiveData
import com.example.ibtechbootcamphmfive.base.BaseCallBack
import com.example.ibtechbootcamphmfive.data.MovieResponse
import com.example.ibtechbootcamphmfive.service.ServiceConnector
import com.example.ibtechbootcamphmfive.util.Constants.LANGUAGE

class PopularMovieRepo {
    val onMoviesFetched = MutableLiveData<MovieResponse>()

    fun getAllMovies(page: Int) {
        ServiceConnector.restInterface.getPopularMovies(LANGUAGE, page)
            .enqueue(object : BaseCallBack<MovieResponse>() {
                override fun onSuccess(data: MovieResponse) {
                    onMoviesFetched.postValue(data)
                }
            })
    }
}