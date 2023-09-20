package com.ent21.nasa.ui.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemEmptyBinding
import com.ent21.nasa.databinding.ItemFeedBinding
import com.ent21.nasa.ui.viewholders.EmptyViewHolder
import com.ent21.nasa.ui.viewholders.FeedViewHolder

enum class ItemContentType {
    EmptyItemType {
        override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*> = EmptyViewHolder(
            ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        override fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item) {}

    },
    FeedItemType {
        override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*> = FeedViewHolder(
            ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

        override fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item) {
            (holder as FeedViewHolder).bind(item as FeedItem)
        }
    };

    abstract fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*>
    abstract fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item)
}