package com.ent21.nasa.ui.viewholders

import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemFeedBinding
import com.ent21.nasa.ui.items.FeedItem

class FeedViewHolder(private val viewBinding: ItemFeedBinding) :
    BaseViewHolder<FeedItem>(viewBinding.root) {

    override fun bind(item: FeedItem): Unit = with(viewBinding) {
        TODO("Not yet implemented")
    }
}