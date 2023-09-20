package com.ent21.nasa.ui.items

import android.graphics.drawable.Drawable
import com.ent21.nasa.core.ItemContentType

data class FeedItem(
    val image: Drawable,
    val title: String,
) : Item {
    override fun getType(): ItemContentType = ItemContentType.FeedItemType
}