package com.example.ibtechbootcamphmfive.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ibtechbootcamphmfive.data.MovieRoom
import com.example.ibtechbootcamphmfive.room.RoomDB

class FavoriteViewModel : ViewModel() {

    var allFavourites: ArrayList<MovieRoom> = ArrayList()

    fun getAllFavourites(context: Context) {
        val favouritesDao = RoomDB.getMovieDatabase(context)?.favouritesDao()
        allFavourites = favouritesDao?.getFavourites() as ArrayList<MovieRoom>
    }

}

