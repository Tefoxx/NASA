package com.ent21.nasa.api.request

data class ApodsByDateRequest(
    val startDate: String,
    val endDate: String,
)

data class ApodsRandomRequest(
    val count: Int
)
