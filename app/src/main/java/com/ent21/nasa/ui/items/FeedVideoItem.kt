package com.ent21.nasa.ui.items

import java.util.Date

data class FeedVideoItem(
    override val id: String,
    val date: Date,
    val explanation: String,
    val thumbnailUrl: String,
    val title: String,
    val videoUrl: String,
    val onClick: () -> Unit
) : Item {
    override fun getType(): ItemContentType = ItemContentType.FeedVideoItemType
}