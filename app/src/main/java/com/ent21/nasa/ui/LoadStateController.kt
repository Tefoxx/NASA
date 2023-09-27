package com.ent21.nasa.ui

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoadStateController(
    val onRefresh: (isRefreshing: Boolean) -> Unit,
    val scrollToTop: (() -> Unit)? = null,
    val onError: () -> Unit,
    scope: CoroutineScope,
) {

    private val loadStateFlow = MutableStateFlow<CombinedLoadStates?>(null)
    private var canScroll = false

    init {
        scope.launch {
            loadStateFlow
                .distinctUntilChangedBy { it?.refresh }
                .mapNotNull { it?.refresh }
                .collect { state ->
                    onRefresh(state is LoadState.Loading)
                    when (state) {
                        is LoadState.NotLoading -> canScroll = true
                        is LoadState.Error -> onError()
                        is LoadState.Loading -> {}
                    }
                }
        }
    }

    /**
    * Because DiffUtil works after receiving the NotLoad status for the ui from the pager adapter
    * and scrollToTop will not work correct. [itemsWasUpdated] synchronizes scrollToTop and DiffUtil
    */
    fun itemsWasUpdated() {
        if (canScroll) {
            scrollToTop?.invoke()
            canScroll = false
        }
    }

    fun updateState(state: CombinedLoadStates) {
        loadStateFlow.value = state
    }
}