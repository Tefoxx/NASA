package com.ent21.nasa.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.items.Item
import com.ent21.nasa.ui.items.ItemContentType

class Adapter : RecyclerView.Adapter<BaseViewHolder<*>>() {
    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val itemType = ItemContentType.values().find { it.ordinal == viewType }
            ?: ItemContentType.EmptyItemType
        return itemType.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val itemType = items[position].getType()
        itemType.onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int) = items[position].getType().ordinal

    override fun getItemCount(): Int = items.size
}