package com.ent21.nasa.ui.viewholders

import android.view.View
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.items.Item

class EmptyViewHolder(view: View): BaseViewHolder<Item>(view) {
    override fun bind(item: Item) {}
}