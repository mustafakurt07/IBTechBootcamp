package com.example.ibtechbootcamphmfive.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.ibtechbootcamphmfive.util.Constants.BASE_PHOTO_PATH
import com.example.ibtechbootcamphmfive.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setUrl(imageView: AppCompatImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(BASE_PHOTO_PATH + "$imageUrl")
            .error(R.drawable.img_not_found)
            .placeholder(R.drawable.img_not_found)
            .into(imageView)
    }
}