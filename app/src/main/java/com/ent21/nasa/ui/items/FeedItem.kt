package com.ent21.nasa.ui.items

import android.graphics.drawable.Drawable

data class FeedItem(
    override val id: String,
    val image: Drawable,
    val title: String,
) : Item {
    override fun getType(): ItemContentType = ItemContentType.FeedItemType
}