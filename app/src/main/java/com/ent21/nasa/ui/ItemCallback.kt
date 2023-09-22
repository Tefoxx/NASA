package com.ent21.nasa.ui

import androidx.recyclerview.widget.DiffUtil
import com.ent21.nasa.ui.items.Item

class ItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.areContentsSame(newItem)
    }

    override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
        return oldItem.getChangePayload(newItem)
    }
}