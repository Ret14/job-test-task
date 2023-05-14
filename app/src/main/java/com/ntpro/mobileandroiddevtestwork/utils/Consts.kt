package com.ntpro.mobileandroiddevtestwork.utils

import com.ntpro.mobileandroiddevtestwork.R

object Consts {
    val dealCardArgsTitles = listOf(
        R.string.deal_id,
        R.string.deal_time,
        R.string.deal_instrument,
        R.string.deal_price,
        R.string.deal_amount,
        R.string.deal_side
    )
    val filterFieldsMap = mapOf(
        Pair("TIME", R.string.deal_time),
        Pair("AMOUNT", R.string.deal_amount),
        Pair("INSTRUMENT", R.string.deal_instrument),
        Pair("PRICE", R.string.deal_price),
        Pair("SIDE", R.string.deal_side),
    )
}