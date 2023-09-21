package com.ent21.nasa.ui.viewholders

import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemFeedBinding
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.utils.load

class FeedViewHolder(private val viewBinding: ItemFeedBinding) :
    BaseViewHolder<FeedItem>(viewBinding.root) {

    override fun bind(item: FeedItem): Unit = with(viewBinding) {
        root.clipToOutline = true
        titleTextView.text = item.title
        descriptionTextView.text = item.explanation
        updateImage(item)
        root.setOnClickListener { item.onClick() }
    }

    private fun updateImage(item: FeedItem) = viewBinding.imageView.load(item.imgUrl)
}