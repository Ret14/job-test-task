package com.ntpro.mobileandroiddevtestwork.data.repository

import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.data.Server
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import kotlinx.coroutines.flow.Flow

interface DealRepository {
    suspend fun createDeals(deals: List<Server.Deal>)
    suspend fun deleteAllDeals()
    fun getFlowByTime(isAsc: Boolean): Flow<PagingData<LocalDeal>>
    fun getFlowByInstrumentName(isAsc: Boolean): Flow<PagingData<LocalDeal>>
    fun getFlowByPrice(isAsc: Boolean): Flow<PagingData<LocalDeal>>
    fun getFlowByAmount(isAsc: Boolean): Flow<PagingData<LocalDeal>>
    fun getFlowBySide(isAsc: Boolean): Flow<PagingData<LocalDeal>>
}