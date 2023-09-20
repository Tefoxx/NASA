package com.ent21.nasa.di

import com.ent21.nasa.ui.feed.FeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { FeedViewModel(get()) }
}