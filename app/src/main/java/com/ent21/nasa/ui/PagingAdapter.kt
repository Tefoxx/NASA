package com.ent21.nasa.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.items.Item
import com.ent21.nasa.ui.items.ItemContentType

class PagingAdapter(
    diffCallback: DiffUtil.ItemCallback<Item>
) : PagingDataAdapter<Item, BaseViewHolder<*>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val itemType = ItemContentType.values().find { it.ordinal == viewType }
            ?: ItemContentType.EmptyItemType
        return itemType.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        getItem(position)?.let {
            it.getType().onBindViewHolder(holder, it)
        }
    }

    override fun getItemViewType(position: Int) =
        getItem(position)?.getType()?.ordinal ?: ItemContentType.EmptyItemType.ordinal
}