package com.ent21.nasa.db

import androidx.room.TypeConverter
import com.ent21.nasa.api.model.MediaType
import java.util.Date

class Converter {
    @TypeConverter
    fun fromDateToTimestamp(date: Date) = date.time

    @TypeConverter
    fun fromTimestampToDate(time: Long) = Date(time)

    @TypeConverter
    fun fromMediaTypeToInt(mediaType: MediaType) = mediaType.value

    @TypeConverter
    fun fromIntToMediaType(value: Int) = MediaType.getByValue(value)
}