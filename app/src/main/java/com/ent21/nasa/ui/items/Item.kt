package com.ent21.nasa.ui.items

interface Item {
    val id: String
    fun getType(): ItemContentType
    fun areItemsSame(newItem: Item): Boolean {
        return id == newItem.id
    }

    fun areContentsSame(newItem: Item): Boolean {
        return hashCode() == newItem.hashCode()
    }
}

