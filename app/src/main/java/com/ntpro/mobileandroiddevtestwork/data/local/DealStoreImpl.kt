package com.ntpro.mobileandroiddevtestwork.data.local

import com.ntpro.mobileandroiddevtestwork.data.Server
import com.ntpro.mobileandroiddevtestwork.data.room.DealsDatabase
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal

class DealStoreImpl(
    database: DealsDatabase
) : DealStore {
    private val deals = database.deals

    override fun getDealsByTime(isAsc: Boolean) = deals.readAllByTime(isAsc)

    override fun getDealsByInstrumentName(isAsc: Boolean) =
        deals.readAllByInstrumentName(isAsc)

    override fun getDealsByPrice(isAsc: Boolean) = deals.readAllByPrice(isAsc)

    override fun getDealsByAmount(isAsc: Boolean) = deals.readAllByAmount(isAsc)
    override fun getDealsBySide(isAsc: Boolean) = deals.readAllBySide(isAsc)

    override suspend fun createDeals(newDeals: List<Server.Deal>) =
        deals.create(newDeals.map { it.toLocalDeal() })

    override suspend fun deleteAllDeals() = deals.deleteAll()

    private fun Server.Deal.toLocalDeal() =
        LocalDeal(id, timeStamp, instrumentName, price, amount, side)

    private fun LocalDeal.toDeal() = Server.Deal(id, timeStamp, instrumentName, price, amount, side)

}