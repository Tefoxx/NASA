package com.ent21.nasa.ui.items

import java.util.UUID

data class EmptyItem(override val id: String = UUID.randomUUID().toString()) : Item {
    override fun getType(): ItemContentType = ItemContentType.EmptyItemType
}