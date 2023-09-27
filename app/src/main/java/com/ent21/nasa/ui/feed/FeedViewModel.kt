package com.ent21.nasa.ui.feed

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
import com.ent21.nasa.utils.SingleLiveEvent
import com.ent21.nasa.utils.toItem

private const val DEFAULT_PAGE_SIZE = 10

class FeedViewModel(
    getFeedPagingUseCaseAsLiveData: GetFeedPagingUseCaseAsLiveData,
) : ViewModel() {

    private val _action = SingleLiveEvent<FeedAction>()
    val action: LiveData<FeedAction> = _action

    val feed = getFeedPagingUseCaseAsLiveData(
        GetFeedPagingUseCaseAsLiveData.Param(DEFAULT_PAGE_SIZE)
    ).cachedIn(viewModelScope).map { data ->
        data.map { toFeedItem(it).toItem() }
    }

    /*
    id = "${apod.date.time}${apod.num}" - This is necessary for diffutil to work correctly (yes,
    diffutil is not needed in this example, but was added for the technical component), because
    NASA api does not provide unique ids in the response (although the date can be used as an id),
    and if it did, the feed is random and has repeating elements (in this case, diffutil would
    still not work correctly)
    */
    private fun toFeedItem(apod: ApodEntity) = FeedItem(
        id = "${apod.date.time}${apod.num}",
        date = apod.date,
        explanation = apod.explanation,
        imgUrl = if (apod.mediaType == MediaType.VIDEO)
            apod.thumbnailUrl ?: "" else apod.hdUrl ?: apod.url,
        title = apod.title,
        videoUrl = if (apod.mediaType == MediaType.VIDEO) apod.url else null,
        num = apod.num
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