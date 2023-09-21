package com.ent21.nasa.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ent21.nasa.api.model.MediaType
import com.ent21.nasa.db.entity.ApodEntity
import com.ent21.nasa.ui.items.EmptyItem
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.ui.items.FeedVideoItem
import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData
import com.ent21.nasa.utils.SingleLiveEvent
import com.ent21.nasa.utils.launchSafe
import kotlinx.coroutines.delay
import java.util.UUID

private const val DEFAULT_PAGE_SIZE = 20

class FeedViewModel(getFeedPagingUseCaseAsLiveData: GetFeedPagingUseCaseAsLiveData) : ViewModel() {

    private val _action = SingleLiveEvent<FeedAction>()
    val action: LiveData<FeedAction> = _action

    val feed = getFeedPagingUseCaseAsLiveData(
        GetFeedPagingUseCaseAsLiveData.Param(DEFAULT_PAGE_SIZE)
    ).cachedIn(viewModelScope).map { data ->
        data.map {
            when (it.mediaType) {
                MediaType.IMAGE -> toFeedItem(it)
                MediaType.VIDEO -> toFeedVideoItem(it)
                else -> {
                    Log.e("PAGING MAPPING", "not supported mediaType")
                    EmptyItem()
                }
            }
        }
    }

    private fun toFeedItem(apod: ApodEntity) = FeedItem(
        id = UUID.randomUUID().toString(),
        date = apod.date,
        explanation = apod.explanation,
        imgUrl = apod.hdUrl ?: apod.url,
        title = apod.title
    ) {
        _action.value = FeedAction.ShowDetails
    }

    private fun toFeedVideoItem(apod: ApodEntity) = FeedVideoItem(
        id = UUID.randomUUID().toString(),
        date = apod.date,
        explanation = apod.explanation,
        thumbnailUrl = apod.thumbnailUrl.orEmpty(),
        title = apod.title,
        videoUrl = apod.url
    ) {
        _action.value = FeedAction.ShowVideoDetails
    }
}

sealed class FeedAction {
    object ShowDetails : FeedAction()
    object ShowVideoDetails : FeedAction()
}