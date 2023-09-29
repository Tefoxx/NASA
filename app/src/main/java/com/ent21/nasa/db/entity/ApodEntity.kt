package com.ent21.nasa.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ent21.nasa.api.model.MediaType
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = ApodEntity.NAME)
data class ApodEntity(
    @PrimaryKey val num: Int,
    val date: Date,
    val explanation: String,
    val hdUrl: String? = null,
    val url: String,
    val thumbnailUrl: String? = null,
    val mediaType: MediaType = MediaType.UNDEFINED,
    val title: String,
) : Parcelable {
    companion object {
        const val NAME = "apods"
    }
}
