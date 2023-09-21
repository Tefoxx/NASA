package com.ent21.nasa.ui.items

import java.util.Date

data class FeedItem(
    override val id: String,
    val date: Date,
    val explanation: String,
    val imgUrl: String,
    val title: String,
    val onClick: () -> Unit
) : Item {
    override fun getType(): ItemContentType = ItemContentType.FeedItemType
}