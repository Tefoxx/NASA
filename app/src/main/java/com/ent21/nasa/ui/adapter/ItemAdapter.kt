package com.ent21.nasa.ui.adapter

import com.ent21.nasa.ui.items.Item

interface ItemAdapter {
    fun getItemByPosition(position: Int): Item?
}