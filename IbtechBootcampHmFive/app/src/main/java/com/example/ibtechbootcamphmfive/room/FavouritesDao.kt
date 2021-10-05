package com.example.ibtechbootcamphmfive.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ibtechbootcamphmfive.data.MovieRoom

@Dao
interface FavouritesDao {
    @Query("SELECT * FROM favourites ORDER BY id DESC")
    fun getFavourites() : List<MovieRoom>

    @Insert
    fun addFavourites(favouriteMovie : MovieRoom?)

    @Delete
    fun deleteFavourites(favouriteMovie: MovieRoom?)
}