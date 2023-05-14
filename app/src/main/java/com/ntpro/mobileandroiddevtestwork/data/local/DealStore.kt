package com.ntpro.mobileandroiddevtestwork.data.local

import androidx.paging.PagingSource
import com.ntpro.mobileandroiddevtestwork.data.Server
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal

interface DealStore {
    fun getDealsByTime(isAsc: Boolean): PagingSource<Int, LocalDeal>
    fun getDealsByInstrumentName(isAsc: Boolean): PagingSource<Int, LocalDeal>
    fun getDealsByPrice(isAsc: Boolean): PagingSource<Int, LocalDeal>
    fun getDealsByAmount(isAsc: Boolean): PagingSource<Int, LocalDeal>
    fun getDealsBySide(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun createDeals(deals: List<Server.Deal>)
    suspend fun deleteAllDeals()
}