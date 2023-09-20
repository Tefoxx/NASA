package com.ent21.nasa.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ent21.nasa.ui.items.Item

abstract class BaseViewHolder<T : Item>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}