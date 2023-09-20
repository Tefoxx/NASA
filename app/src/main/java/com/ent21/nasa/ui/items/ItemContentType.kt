package com.ent21.nasa.ui.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ent21.nasa.R
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.viewholders.EmptyViewHolder
import com.ent21.nasa.ui.viewholders.FeedViewHolder

enum class ItemContentType {
    EmptyItemType {
        override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*> = EmptyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
        )

        override fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item) { }

    },
    FeedItemType {
        override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*> = FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        )

        override fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item) {
            (holder as FeedViewHolder).bind(item as FeedItem)
        }
    };

    abstract fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<*>
    abstract fun onBindViewHolder(holder: BaseViewHolder<*>, item: Item)
}