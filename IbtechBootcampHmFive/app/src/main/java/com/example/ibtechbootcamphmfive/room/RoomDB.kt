package com.example.ibtechbootcamphmfive.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ibtechbootcamphmfive.data.MovieRoom


@Database(entities = [MovieRoom::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao?

    companion object {
        private var INSTANCE: RoomDB? = null

        @TypeConverters
        fun getMovieDatabase(context: Context): RoomDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "MovieDB"
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }
    }
}