package com.ent21.nasa.usecase

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.ent21.nasa.core.UseCaseAsLiveData
import com.ent21.nasa.db.entity.ApodEntity
import com.ent21.nasa.db.gateway.ApodLocalGateway
import com.ent21.nasa.ui.feed.paging.FeedRemoteMediator
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


class GetFeedPagingUseCaseAsLiveData(
    private val localGateway: ApodLocalGateway
) : UseCaseAsLiveData<GetFeedPagingUseCaseAsLiveData.Param, PagingData<ApodEntity>>(), KoinComponent {

    @OptIn(ExperimentalPagingApi::class)
    override fun execute(params: Param): LiveData<PagingData<ApodEntity>> {
        val mediator = get<FeedRemoteMediator>()
        return Pager(
            config = PagingConfig(params.pageSize),
            remoteMediator = mediator
        ) {
            localGateway.getSource()
        }.liveData
    }

    data class Param(val pageSize: Int)
}