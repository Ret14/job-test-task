package com.ntpro.mobileandroiddevtestwork.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ntpro.mobileandroiddevtestwork.data.room.dao.DealDao
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal


@Database(entities = [LocalDeal::class], version = 1)
@TypeConverters(DealConverters::class)
abstract class DealsDatabase: RoomDatabase() {
    abstract val deals: DealDao
}