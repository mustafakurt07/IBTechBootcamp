package com.example.ibtechbootcamphmfive.service

import com.example.ibtechbootcamphmfive.data.MovieResponse
import com.example.ibtechbootcamphmfive.data.detail.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("popular")
    fun getPopularMovies(
        @Query("language") lang: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("{movie_id}")
    fun getMovieDetailById(
        @Path("movie_id") movie_id: Int,
        @Query("language") lang: String
    ): Call<MovieDetails>

}