package com.ent21.nasa.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.ent21.nasa.ui.MainNavigation
import com.ent21.nasa.ui.items.Item
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File

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

fun Fragment.toast(@StringRes textResId: Int) =
    Toast.makeText(context, getString(textResId), Toast.LENGTH_LONG).show()

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

fun Fragment.getMainNav() = (activity as? MainNavigation)?.getMainNavController()

fun String.applyHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
    Html.fromHtml(this)
}

fun Activity.showPicture(url: String) = startActivity(Intent().run {
    action = Intent.ACTION_VIEW
    setDataAndType(url.toUri(), "image/*")
})

fun Fragment.showPicture(url: String) = activity?.showPicture(url)

fun StringBuilder.appendSeparator(): StringBuilder = append(File.separator)