package com.example.countrieskotlin.view

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countrieskotlin.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    // Glide customization to show an image on loading error
    val options = RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher_round)
    // Download and load image into the ImageView using Glide instance
    Glide.with(this)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}