package com.ent21.nasa.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpaceItemDecorator(
    private val left: Int,
    private val top: Int,
    private val right: Int,
    private val bottom: Int,
    private val divider: Int,
    private val orientation: Int = RecyclerView.VERTICAL
) : RecyclerView.ItemDecoration() {

    constructor(margin: Int, divider: Int) : this(margin, margin, margin, margin, divider)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view).takeIf {
            it != RecyclerView.NO_POSITION
        } ?: return

        val lastPosition = state.itemCount - 1

        if (orientation == RecyclerView.VERTICAL) {
            outRect.left = left
            outRect.right = right
            if (position == 0) outRect.top = top
            if (position == lastPosition) outRect.bottom = bottom else outRect.bottom = divider
        } else {
            outRect.top = top
            outRect.bottom = bottom
            if (position == 0) outRect.left = left
            if (position == lastPosition) outRect.right = right else outRect.right = divider
        }
    }
}