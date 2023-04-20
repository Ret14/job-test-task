package com.ntpro.mobileandroiddevtestwork.data.room

import androidx.room.Entity
import com.ntpro.mobileandroiddevtestwork.Server
import java.util.*

@Entity(tableName = "deal")
data class LocalDeal(
    val id: Long,
    val timeStamp: Date,
    val instrumentName: String,
    val price: Double,
    val amount: Double,
    val side: Server.Deal.Side,
)