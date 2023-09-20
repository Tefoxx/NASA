package com.ent21.nasa.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData

private const val DEFAULT_PAGE_SIZE = 20

class FeedViewModel(getFeedPagingUseCaseAsLiveData: GetFeedPagingUseCaseAsLiveData) : ViewModel() {
    val feed = getFeedPagingUseCaseAsLiveData(
        GetFeedPagingUseCaseAsLiveData.Param(DEFAULT_PAGE_SIZE)
    ).cachedIn(viewModelScope)
}