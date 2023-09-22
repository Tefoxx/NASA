package com.ent21.nasa.ui.viewholders

import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemFeedBinding
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.utils.load
import java.text.SimpleDateFormat
import java.util.*

class FeedViewHolder(private val viewBinding: ItemFeedBinding) :
    BaseViewHolder<FeedItem>(viewBinding.root) {

    private val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.ROOT)

    override fun bind(item: FeedItem): Unit = with(viewBinding) {
        root.clipToOutline = true
        titleTextView.text = item.title
        descriptionTextView.text = item.explanation
        updateImage(item)
        root.setOnClickListener { item.onClick() }
    }

    override fun update(item: FeedItem, payloads: Set<*>) = payloads.forEach {
            when(it) {
                FeedItem.DATE_KEY -> updateDate(item)
                FeedItem.IMG_URL_KEY -> updateImage(item)
                FeedItem.EXPLANATION_KEY -> updateDescription(item)
                FeedItem.TITLE_KEY -> updateTitle(item)
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

    private fun updateImage(item: FeedItem) = viewBinding.imageView.load(item.imgUrl)
}