package com.ntpro.mobileandroiddevtestwork.data.room

import androidx.room.TypeConverter
import java.util.*

class DealConverters {
    @TypeConverter
    fun fromTimestamp(value: Long) = Date(value)
    @TypeConverter
    fun dateToTimestamp(date: Date) = date.time
}