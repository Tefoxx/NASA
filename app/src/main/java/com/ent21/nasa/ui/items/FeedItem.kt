package com.ent21.nasa.ui.items

import android.os.Bundle
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

    //in this case it is not necessary, but for example
    override fun areContentsSame(newItem: Item): Boolean {
        newItem as FeedItem
        return title == newItem.title
                && explanation == newItem.explanation
                && imgUrl == newItem.imgUrl
                && date == newItem.date
    }

    //in this case it is not necessary, but for example
    override fun getChangePayload(newItem: Item): Any? {
        newItem as FeedItem
        val diff = mutableSetOf<String>()
        if (title != newItem.title) diff.add(TITLE_KEY)
        if (explanation != newItem.explanation) diff.add(EXPLANATION_KEY)
        if (imgUrl != newItem.imgUrl) diff.add(IMG_URL_KEY)
        if (date != newItem.date) diff.add(DATE_KEY)
        return diff
    }

    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val EXPLANATION_KEY = "EXPLANATION_KEY"
        const val IMG_URL_KEY = "IMG_URL_KEY"
        const val DATE_KEY = "DATE_KEY"
    }
}