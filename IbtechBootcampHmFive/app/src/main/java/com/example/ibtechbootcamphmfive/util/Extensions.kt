package com.example.ibtechbootcamphmfive.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toastShort(messageToShow: String) {
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(messageToShow: String) {
    Toast.makeText(requireContext(), messageToShow, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
fun Fragment.statusBarColor(fragmentId:Int)
{
    activity?.window?.statusBarColor = resources.getColor(fragmentId)

}