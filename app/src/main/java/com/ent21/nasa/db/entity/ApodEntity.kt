package com.ent21.nasa.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ent21.nasa.api.model.MediaType
import java.util.*

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
) {
    companion object {
        const val NAME = "apods"
    }
}
