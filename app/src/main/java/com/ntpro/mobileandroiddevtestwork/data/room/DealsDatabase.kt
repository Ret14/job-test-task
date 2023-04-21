package com.ntpro.mobileandroiddevtestwork.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalDeal::class], version = 1)
abstract class DealsDatabase: RoomDatabase() {
    abstract val deals: DealDao
}