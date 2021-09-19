package com.example.ibtechbootcamphm3

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline fun Fragment.navigateToNextFragment(@IdRes id: Int, block: Bundle.() -> Unit = {}) {
    findNavController().navigate(id, Bundle().apply(block))
}
fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}
//function for status bar color
fun Fragment.statusBarColor(fragmentId:Int)
{
   activity?.window?.statusBarColor = resources.getColor(fragmentId)

}