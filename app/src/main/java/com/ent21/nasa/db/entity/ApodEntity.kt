package com.ent21.nasa.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ApodEntity(
    @PrimaryKey val date: Date,
    val explanation: String,
    val hdUrl: String?,
    val url: String,
    val thumbnailUrl: String?,
    val mediaType: String,
    val title: String,
)
