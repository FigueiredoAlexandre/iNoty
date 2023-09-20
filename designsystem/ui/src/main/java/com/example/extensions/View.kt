package com.example.extensions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

fun View.visible() {
    this.isVisible = !isVisible
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadUri(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .into(this)
}