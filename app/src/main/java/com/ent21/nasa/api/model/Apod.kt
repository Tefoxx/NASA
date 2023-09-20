package com.ent21.nasa.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Apod(
    @SerializedName("date") val date: Date,
    @SerializedName("explanation") val explanation: String,
    @SerializedName("hdurl") val hdUrl: String?,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String?,
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("title") val title: String,
) : Parcelable
