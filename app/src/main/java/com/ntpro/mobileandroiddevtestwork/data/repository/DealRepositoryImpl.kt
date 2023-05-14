package com.ntpro.mobileandroiddevtestwork.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.local.DealStore
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DealRepositoryImpl(
    private val dealStore: DealStore,
): DealRepository {
    override suspend fun createDeals(deals: List<Server.Deal>) = dealStore.createDeals(deals)

    override suspend fun deleteAllDeals() = dealStore.deleteAllDeals()

    override fun getFlowByTime(isAsc: Boolean): Flow<PagingData<LocalDeal>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = { dealStore.getDealsByTime(isAsc) }
            ).flow
    }

    override fun getFlowByInstrumentName(isAsc: Boolean): Flow<PagingData<LocalDeal>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = { dealStore.getDealsByInstrumentName(isAsc) }
        ).flow
    }

    override fun getFlowByPrice(isAsc: Boolean): Flow<PagingData<LocalDeal>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = { dealStore.getDealsByPrice(isAsc) }
        ).flow
    }

    override fun getFlowByAmount(isAsc: Boolean): Flow<PagingData<LocalDeal>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = { dealStore.getDealsByAmount(isAsc) }
        ).flow
    }

    override fun getFlowBySide(isAsc: Boolean): Flow<PagingData<LocalDeal>> {
        return Pager(
            config = PAGING_CONFIG,
            pagingSourceFactory = { dealStore.getDealsBySide(isAsc) }
        ).flow
    }

    companion object {
         val PAGING_CONFIG = PagingConfig(pageSize = 20, enablePlaceholders = true)
    }

}