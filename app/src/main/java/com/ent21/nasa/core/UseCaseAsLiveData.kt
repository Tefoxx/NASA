package com.ent21.nasa.core

import androidx.lifecycle.LiveData

abstract class UseCaseAsLiveData<in Params, Result> {
    protected abstract fun execute(params: Params): LiveData<Result>

    operator fun invoke(params: Params): LiveData<Result> {
        return execute(params)
    }
}