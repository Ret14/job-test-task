package com.ntpro.mobileandroiddevtestwork.data.local

import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.room.DealsDatabase
import com.ntpro.mobileandroiddevtestwork.data.room.LocalDeal

class DealStoreImpl(
    database: DealsDatabase
) : DealStore {
    private val deals = database.deals

    override suspend fun getDealsByTime(isAsc: Boolean) = deals.readAllByTime(isAsc)

    override suspend fun getDealsByInstrumentName(isAsc: Boolean) =
        deals.readAllByInstrumentName(isAsc)

    override suspend fun getDealsByPrice(isAsc: Boolean) = deals.readAllByPrice(isAsc)

    override suspend fun getDealsByAmount(isAsc: Boolean) = deals.readAllByAmount(isAsc)
    override suspend fun getDealsBySide(isAsc: Boolean) = deals.readAllBySide(isAsc)

    override suspend fun createDeals(newDeals: List<Server.Deal>) = deals.create(newDeals.map { it.toLocalDeal() })

    override suspend fun deleteAllDeals() = deals.deleteAll()

    private fun Server.Deal.toLocalDeal() =
        LocalDeal(id, timeStamp, instrumentName, price, amount, side)

}