package com.ent21.nasa.ui.viewholders

import android.graphics.drawable.Drawable
import androidx.core.view.isVisible
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemFeedBinding
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.utils.getDp
import com.ent21.nasa.utils.load
import com.ent21.nasa.utils.updateMargin
import java.text.SimpleDateFormat
import java.util.*

private const val VIDEO_ICON_MARGIN = 8

class FeedViewHolder(private val viewBinding: ItemFeedBinding) :
    BaseViewHolder<FeedItem>(viewBinding.root) {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    private val imgListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean = with(viewBinding) {
            errorGroup.isVisible = true
            shimmerLayout.hideShimmer()
            false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>?,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {
            viewBinding.shimmerLayout.hideShimmer()
            return false
        }

    }

    override fun bind(item: FeedItem): Unit = with(viewBinding) {
        root.clipToOutline = true
        shimmerLayout.showShimmer(true)
        updateTitle(item)
        updateDescription(item)
        updateImage(item)
        updateDate(item)
        updateVideoIcon(item)
        root.setOnClickListener { item.onClick() }
    }

    override fun update(item: FeedItem, payloads: Set<*>) = payloads.forEach {
        when (it) {
            FeedItem.DATE_KEY -> updateDate(item)
            FeedItem.IMG_URL_KEY -> updateImage(item)
            FeedItem.EXPLANATION_KEY -> updateDescription(item)
            FeedItem.TITLE_KEY -> updateTitle(item)
            FeedItem.VIDEO_URL_KEY -> updateVideoIcon(item)
        }
    }

    private fun updateTitle(item: FeedItem) = with(viewBinding) {
        titleTextView.text = item.title
    }

    private fun updateDate(item: FeedItem) = with(viewBinding) {
        dateTextView.text = dateFormat.format(item.date)
    }

    private fun updateDescription(item: FeedItem) = with(viewBinding) {
        descriptionTextView.text = item.explanation
    }

    private fun updateImage(item: FeedItem) = with(viewBinding) {
        errorGroup.isVisible = false
        imageView.load(
            url = item.imgUrl,
            listener = imgListener
        )
    }

    private fun updateVideoIcon(item: FeedItem) = with(viewBinding) {
        videoIconView.isVisible = !item.videoUrl.isNullOrEmpty()
        titleTextView.updateMargin(
            left = if (item.videoUrl.isNullOrEmpty()) 0 else titleTextView.context.getDp(
                VIDEO_ICON_MARGIN
            )
        )
    }
}