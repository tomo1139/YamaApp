package com.example.tomo.yamaapp.util.extension

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by tomo on 2018/04/06.
 */
@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        return
    }
    Picasso.with(context).load(imageUrl).into(this)
}