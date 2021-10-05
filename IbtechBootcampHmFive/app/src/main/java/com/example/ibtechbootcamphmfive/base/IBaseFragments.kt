package com.example.ibtechbootcamphmfive.base

import com.example.ibtechbootcamphmfive.R

interface IBaseFragments {
        /**
         * We mark ShouldCheckInternetConnection as false cause of we need to call this function where we need it.
         */
        fun shouldCheckInternetConnection() = false
        fun getStatusBarColor() = R.color.white
        fun networkConnection(): Boolean

}