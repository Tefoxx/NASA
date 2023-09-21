package com.ent21.nasa.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launchSafe(
    body: suspend () -> Unit,
    onError: (error: Throwable) -> Unit,
    start: (() -> Unit)? = null,
    final: (() -> Unit)? = null
): Job = viewModelScope.launch {
    try {
        start?.invoke()
        body()
    } catch (error: Exception) {
        onError(error)
    } finally {
        final?.invoke()
    }
}

fun ImageView.load(
    url: String,
    @DrawableRes placeholder: Int? = null,
    onFailed: ((GlideException?) -> Unit)? = null,
    onLoaded: ((Drawable) -> Unit)? = null
) {
    val options = RequestOptions()
    placeholder?.let { options.placeholder(placeholder) }
    Glide.with(this).load(url).apply(options).listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            onFailed?.invoke(e)
            return true
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            onLoaded?.invoke(resource)
            return true
        }
    }).into(this)
}