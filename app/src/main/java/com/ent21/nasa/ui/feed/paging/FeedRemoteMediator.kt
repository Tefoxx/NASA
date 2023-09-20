package com.ent21.nasa.ui.feed.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ent21.nasa.api.gateway.ApodRemoteGateway
import com.ent21.nasa.db.entity.ApodEntity
import com.ent21.nasa.db.gateway.ApodLocalGateway

private const val DEFAULT_COUNT_LOAD = 20

@OptIn(ExperimentalPagingApi::class)
class FeedRemoteMediator(
    private val localGateway: ApodLocalGateway,
    private val remoteGateway: ApodRemoteGateway
) : RemoteMediator<Int, ApodEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ApodEntity>
    ): MediatorResult {
        return try {
            when (loadType) {
                LoadType.REFRESH -> { }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> localGateway.getLastItem()
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            val response = remoteGateway.getApodsRandom(DEFAULT_COUNT_LOAD)
            if (loadType == LoadType.REFRESH) {
                localGateway.clearAndInsertAll(response)
            } else {
                localGateway.insertAll(response)
            }

            // no nextKey
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}