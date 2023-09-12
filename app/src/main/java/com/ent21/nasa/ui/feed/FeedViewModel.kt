package com.ent21.nasa.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ent21.nasa.api.model.MediaType
import com.ent21.nasa.db.entity.ApodEntity
import com.ent21.nasa.ui.items.FeedItem
import com.ent21.nasa.usecase.GetFeedPagingUseCaseAsLiveData
import com.ent21.nasa.usecase.UpdateFeedUseCase
import com.ent21.nasa.utils.SingleLiveEvent
import com.ent21.nasa.utils.launchSafe
import com.ent21.nasa.utils.toItem
import java.util.*

private const val DEFAULT_PAGE_SIZE = 10

class FeedViewModel(
    private val updateFeedUseCase: UpdateFeedUseCase,
    getFeedPagingUseCaseAsLiveData: GetFeedPagingUseCaseAsLiveData,
) : ViewModel() {

    private val _action = SingleLiveEvent<FeedAction>()
    val action: LiveData<FeedAction> = _action

    val feed = getFeedPagingUseCaseAsLiveData(
        GetFeedPagingUseCaseAsLiveData.Param(DEFAULT_PAGE_SIZE)
    ).cachedIn(viewModelScope).map { data ->
        data.map { toFeedItem(it).toItem() }
    }

    fun update(onErrorMessage: String? = null) {
        launchSafe(
            body = { updateFeedUseCase(UpdateFeedUseCase.Param(DEFAULT_PAGE_SIZE)) },
            onError = { throwable ->
                onErrorMessage?.let { _action.value = FeedAction.ShowToast(it) }
                Log.e("SNK", "Could not update feed", throwable)
            },
            final = { _action.value = FeedAction.HideRefresh }
        )
    }

    private fun toFeedItem(apod: ApodEntity) = FeedItem(
        id = UUID.randomUUID().toString(),
        date = apod.date,
        explanation = apod.explanation,
        imgUrl = if (apod.mediaType == MediaType.VIDEO)
            apod.thumbnailUrl ?: "" else apod.hdUrl ?: apod.url,
        title = apod.title,
        videoUrl = if (apod.mediaType == MediaType.VIDEO) apod.url else null
    ) {
        _action.value = if (apod.mediaType == MediaType.VIDEO)
            FeedAction.ShowVideoDetails else FeedAction.ShowDetails
    }
}

sealed class FeedAction {
    object ShowDetails : FeedAction()
    object ShowVideoDetails : FeedAction()
    object HideRefresh : FeedAction()
    data class ShowToast(val text: String) : FeedAction()
}