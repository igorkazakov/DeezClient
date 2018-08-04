package com.example.igor.deezclient.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.Toolbar
import com.example.igor.deezclient.R
import com.facebook.drawee.view.SimpleDraweeView

object CustomBindings {

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: SimpleDraweeView, imageUrl: String) {
        view.setImageURI(imageUrl)
    }

    @BindingAdapter("app:titleTextColor1")
    @JvmStatic fun toolbarColor(view: Toolbar, imageUrl: String) {
        view.setTitleTextColor(android.graphics.Color.RED)
    }
}