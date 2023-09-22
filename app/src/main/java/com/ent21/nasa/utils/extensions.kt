package com.ent21.nasa.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.ent21.nasa.ui.items.Item
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
    @DrawableRes placeholderId: Int? = null,
    @DrawableRes errorImgId: Int? = null,
    listener: RequestListener<Drawable>? = null,
) {
    val options = RequestOptions()
    placeholderId?.let { options.placeholder(placeholderId) }
    errorImgId?.let { options.error(errorImgId) }
    Glide.with(this).load(url).apply(options).listener(listener).into(this)
}

fun Context.getDp(value: Int): Int = (resources.displayMetrics.density * value).toInt()

fun Fragment.toast(text: String) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()

fun View.updateMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) = (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
    it.setMargins(
        left ?: it.leftMargin,
        top ?: it.topMargin,
        right ?: it.rightMargin,
        bottom ?: it.bottomMargin
    )
}

fun <T : Item> T.toItem(): Item = this