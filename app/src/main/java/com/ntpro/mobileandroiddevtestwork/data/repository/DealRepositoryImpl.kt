package com.ntpro.mobileandroiddevtestwork.data.repository

import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.local.DealStore
import kotlinx.coroutines.channels.Channel

class DealRepositoryImpl(
    private val dealStore: DealStore,
    val dealRemote: Server
): DealRepository {
    override suspend fun getDealsByTime(isAsc: Boolean) = dealStore.getDealsByTime(isAsc)

    override suspend fun getDealsByInstrumentName(isAsc: Boolean) = dealStore.getDealsByInstrumentName(isAsc)

    override suspend fun getDealsByPrice(isAsc: Boolean) = dealStore.getDealsByPrice(isAsc)

    override suspend fun getDealsByAmount(isAsc: Boolean) = dealStore.getDealsByAmount(isAsc)

    override suspend fun getDealsBySide(isAsc: Boolean) = dealStore.getDealsBySide(isAsc)

    override suspend fun createDeals(deals: List<Server.Deal>) = dealStore.createDeals(deals)

    override suspend fun deleteAllDeals() = dealStore.deleteAllDeals()
    override fun getDataChannel(): Channel<List<Server.Deal>> = dataChannel

    private val dataChannel = Channel<List<Server.Deal>>()

}