package com.ent21.nasa.ui.viewholders

import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.databinding.ItemEmptyBinding
import com.ent21.nasa.ui.items.Item

class EmptyViewHolder(private val viewBinding: ItemEmptyBinding) :
    BaseViewHolder<Item>(viewBinding.root) {

    override fun bind(item: Item) {}
}