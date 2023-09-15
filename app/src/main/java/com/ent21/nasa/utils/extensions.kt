package com.ent21.nasa.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launchSafe(
    body: suspend () -> Unit,
    onError: (error: Throwable) -> Unit,
    start: (() -> Unit)? = null,
    final: (() -> Unit)? = null
): Job = viewModelScope.launch {
    try {
        start?.invoke()
        body()
    } catch (error: Exception) {
        onError(error)
    } finally {
        final?.invoke()
    }
}
