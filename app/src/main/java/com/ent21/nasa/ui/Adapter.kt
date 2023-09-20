package com.ent21.nasa.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ent21.nasa.core.BaseViewHolder
import com.ent21.nasa.ui.items.Item
import com.ent21.nasa.ui.items.ItemContentType

class Adapter(
    diffCallback: DiffUtil.ItemCallback<Item>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    fun setCollection(list: List<Item>) = differ.submitList(list)

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<*> {
        val itemType = ItemContentType.values().find { it.ordinal == viewType }
            ?: ItemContentType.EmptyItemType
        return itemType.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) =
        differ.currentList[position].let {
            it.getType().onBindViewHolder(holder, it)
        }

    override fun getItemViewType(position: Int) = differ.currentList[position].getType().ordinal

    override fun getItemCount(): Int = differ.currentList.size
}