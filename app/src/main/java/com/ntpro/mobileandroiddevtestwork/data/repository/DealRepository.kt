package com.ntpro.mobileandroiddevtestwork.data.repository

import androidx.paging.PagingSource
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    suspend fun getDealsByTime(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun getDealsByInstrumentName(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun getDealsByPrice(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun getDealsByAmount(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun getDealsBySide(isAsc: Boolean): PagingSource<Int, LocalDeal>
    suspend fun createDeals(deals: List<Server.Deal>)
    suspend fun deleteAllDeals()
}