package com.ent21.nasa.di

import com.ent21.nasa.ui.Adapter
import com.ent21.nasa.ui.ItemCallback
import com.ent21.nasa.ui.PagingAdapter
import com.ent21.nasa.ui.feed.FeedViewModel
import com.ent21.nasa.ui.items.Item
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { FeedViewModel(get()) }
    factory { ItemCallback() }
    factory { Adapter(get()) }
    factory { PagingAdapter(get()) }
}