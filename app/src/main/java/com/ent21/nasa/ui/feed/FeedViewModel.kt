package com.ent21.nasa.ui.feed

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ent21.nasa.R
import com.ent21.nasa.api.model.MediaType
import com.ent21.nasa.db.entity.ApodEntity
import com.ent21.nasa.utils.LoadStateController
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData
import com.ent21.nasa.utils.SingleLiveEvent
import com.ent21.nasa.utils.toItem

private const val DEFAULT_PAGE_SIZE = 10

class FeedViewModel(
    getFeedPagingUseCaseAsLiveData: GetFeedPagingUseCaseAsLiveData,
) : ViewModel() {

    private val _action = SingleLiveEvent<FeedAction>()
    val action: LiveData<FeedAction> = _action

    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing = _isRefreshing

    val feed = getFeedPagingUseCaseAsLiveData(
        GetFeedPagingUseCaseAsLiveData.Param(DEFAULT_PAGE_SIZE)
    ).map { data ->
        data.map { toFeedItem(it).toItem() }
    }.cachedIn(viewModelScope)

    val loadStateController = LoadStateController(
        onRefresh = { _isRefreshing.value = it },
        scrollToTop = { _action.value = FeedAction.ScrollToPosition(0) },
        onError = { _action.value = FeedAction.ShowToast(R.string.update_error) },
        scope = viewModelScope
    )

    private fun toFeedItem(apod: ApodEntity) = FeedItem(
        date = apod.date,
        explanation = apod.explanation,
        imgUrl = if (apod.mediaType == MediaType.VIDEO)
            apod.thumbnailUrl ?: "" else apod.hdUrl ?: apod.url,
        title = apod.title,
        videoUrl = if (apod.mediaType == MediaType.VIDEO) apod.url else null,
        num = apod.num
    ) {
        _action.value = if (apod.mediaType == MediaType.VIDEO)
            FeedAction.ShowVideoDetails(apod) else FeedAction.ShowDetails(apod)
    }
}

sealed class FeedAction {
    data class ShowDetails(val apod: ApodEntity) : FeedAction()
    data class ShowVideoDetails(val apod: ApodEntity) : FeedAction()
    data class ScrollToPosition(val position: Int) : FeedAction()
    data class ShowToast(@StringRes val textResId: Int) : FeedAction()
}