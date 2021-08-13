package com.goplay.home.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.goplay.core.BuildConfig
import com.goplay.home.R
import java.util.regex.Matcher
import java.util.regex.Pattern

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_URL + url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.rectangle_placeholder)
        .error(R.drawable.image_broken)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("imageCircleUrl")
fun ImageView.loadCircleImage(url: String) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_URL + url)
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.circle_placeholder)
        .error(R.drawable.ic_broken_image)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun capitalize(str: String, type: String): String {
    val capBuffer = StringBuffer()
    val capMatcher: Matcher = Pattern.compile(
        "([a-z])([a-z]*)",
        Pattern.CASE_INSENSITIVE
    ).matcher(str)

    while (capMatcher.find()) {
        capMatcher.appendReplacement(
            capBuffer,
            capMatcher.group(1).uppercase() + capMatcher.group(2).lowercase()
        )
    }

    return capMatcher.appendTail(capBuffer).toString().replace("_", " ").plus(type)
}