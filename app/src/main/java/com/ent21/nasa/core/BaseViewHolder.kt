package com.ent21.nasa.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ent21.nasa.ui.adapter.ItemAdapter
import com.ent21.nasa.ui.items.Item

abstract class BaseViewHolder<T : Item>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)

    open fun update(item: T, payloads: Set<*>) {}

    @Suppress("UNCHECKED_CAST")
    fun getItem(): T? =
        (bindingAdapter as? ItemAdapter)?.getItemByPosition(absoluteAdapterPosition) as? T
}