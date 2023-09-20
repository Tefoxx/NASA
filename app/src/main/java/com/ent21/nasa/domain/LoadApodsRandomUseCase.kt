package com.ent21.nasa.domain

import com.ent21.nasa.api.gateway.ApodRemoteGateway
import com.ent21.nasa.core.UseCase
import com.ent21.nasa.db.gateway.ApodLocalGateway

class LoadApodsRandomUseCase(
    private val remoteGateway: ApodRemoteGateway,
    private val localGateway: ApodLocalGateway,
) : UseCase<LoadApodsRandomUseCase.Params, Unit>() {
    override suspend fun execute(params: Params) {
        localGateway.saveApods(
            remoteGateway.getApodsRandom(params.count)
        )
    }

    data class Params(val count: Int)
}