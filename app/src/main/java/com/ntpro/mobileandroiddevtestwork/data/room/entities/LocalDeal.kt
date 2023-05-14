package com.ntpro.mobileandroiddevtestwork.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntpro.mobileandroiddevtestwork.data.Server
import java.util.*

@Entity(tableName = "deal")
data class LocalDeal(
    @PrimaryKey
    val id: Long,
    val timeStamp: Date,
    val instrumentName: String,
    val price: Double,
    val amount: Double,
    val side: Server.Deal.Side,
)