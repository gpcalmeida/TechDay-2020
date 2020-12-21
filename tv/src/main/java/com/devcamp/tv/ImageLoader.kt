package com.devcamp.tv

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object ImageLoader {
    fun load(context: Context, url: String, imageView: ImageView) {
        val options = RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(context).load(url).apply(options).into(imageView)
    }

    fun loadFitCenter(context: Context, url: String?, placeholder: Int?, imageView: ImageView) {
        val options = RequestOptions()
            .centerCrop()
            .priority(Priority.NORMAL)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)

        if (placeholder != null)
            options.placeholder(placeholder)

        Glide.with(context).load(url).apply(options).into(imageView)
    }

    fun loadNoCache(context: Context, url: String, placeholder: Int?, imageView: ImageView) {
        val options = RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        if (placeholder != null)
            options.placeholder(placeholder)

        Glide.with(context).load(url).apply(options).into(imageView)
    }
}