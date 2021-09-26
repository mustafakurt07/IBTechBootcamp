package com.example.ibtechbootcamphmfour.utils

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.content.SharedPreferences

import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatActivity


inline fun Fragment.navigateToNextFragment(@IdRes id: Int, block: Bundle.() -> Unit = {}) {
    findNavController().navigate(id, Bundle().apply(block))
}


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun Fragment.toast(messageToShow: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), messageToShow, duration).show()
}

fun AppCompatActivity.toast(messageToShow: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, messageToShow, duration).show()
}

fun EditText.getString(): String {

    return this.text.toString()

}

fun Fragment.saveDataAsString(key : String, value : String){
    val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE)

    val myEdit = sharedPreferences.edit()

    myEdit.putString(key, value)

    myEdit.commit()
}

fun Fragment.statusBarColor(fragmentId:Int)
{
    activity?.window?.statusBarColor = resources.getColor(fragmentId)

}