package com.ent21.nasa.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.items.Item
import com.ent21.nasa.ui.items.ItemContentType

class PagingAdapter(
    diffCallback: DiffUtil.ItemCallback<Item>
) : PagingDataAdapter<Item, BaseViewHolder<*>>(diffCallback), ItemAdapter {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val itemType = ItemContentType.values().find { it.ordinal == viewType }
            ?: ItemContentType.EmptyItemType
        return itemType.onCreateViewHolder(parent)
    }

    override fun getItemViewType(position: Int) =
        getItem(position)?.getType()?.ordinal ?: ItemContentType.EmptyItemType.ordinal

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) =
        onBindHolder(holder, position)

    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>
    ) = onBindHolder(holder, position, payloads)

    @Suppress("UNCHECKED_CAST")
    private fun onBindHolder(
        holder: BaseViewHolder<*>,
        position: Int,
        payloads: MutableList<Any>? = null
    ) {
        holder as BaseViewHolder<Item>
        getItem(position)?.let {
            if (payloads.isNullOrEmpty()) holder.bind(it) else holder.update(it, payloads[0] as Set<*>)
        }
    }

    override fun getItemByPosition(position: Int): Item? = getItem(position)
}