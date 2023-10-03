package com.ent21.nasa.ui.detail

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ent21.nasa.R
import com.ent21.nasa.utils.DownloadManager
import com.ent21.nasa.utils.SingleLiveEvent
import com.ent21.nasa.utils.launchSafe

class DetailViewModel(
    private val downloadManager: DownloadManager,
    private val args: DetailFragmentArgs
) : ViewModel() {

    private val _action = SingleLiveEvent<DetailAction>()
    val action: LiveData<DetailAction> = _action
    fun saveImageInGallery() = launchSafe(
        body = {
            with(args.apod) {
                downloadManager.saveImageInGallery(
                    title = title,
                    url = hdUrl ?: url,
                    permissionRequest = { _action.postValue(DetailAction.PermissionRequest(it)) }
                )
            }
        },
        onError = { _action.value = DetailAction.ShowToast(R.string.error_message) }
    )

    fun onPermissionResult(isGranted: Boolean) {
        if (isGranted) { saveImageInGallery() }
    }
}

sealed class DetailAction {
    data class ShowToast(@StringRes val textResId: Int) : DetailAction()
    data class PermissionRequest(val permission: String) : DetailAction()
}