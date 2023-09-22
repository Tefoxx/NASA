package com.ent21.nasa.usecase

import com.ent21.nasa.api.gateway.ApodRemoteGateway
import com.ent21.nasa.core.UseCase
import com.ent21.nasa.db.gateway.ApodLocalGateway

class UpdateFeedUseCase(
    private val localGateway: ApodLocalGateway,
    private val remoteGateway: ApodRemoteGateway,
) : UseCase<UpdateFeedUseCase.Param, Unit>() {
    override suspend fun execute(params: Param) {
        localGateway.clearAndInsertAll(remoteGateway.getApodsRandom(params.pageSize))
    }

    data class Param(val pageSize: Int)
}