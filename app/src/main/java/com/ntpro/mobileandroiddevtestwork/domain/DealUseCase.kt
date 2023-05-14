package com.ntpro.mobileandroiddevtestwork.domain

import com.ntpro.mobileandroiddevtestwork.Server
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class DealUseCase @Inject constructor(private val api: Server) {
    operator fun invoke(): Flow<List<Server.Deal>> = flow {
        var isUpdate = false
        var newDeals: List<Server.Deal> = emptyList()
        var currentTime = Date().time
        var newDealsTime = currentTime
        while (currentTime - newDealsTime <= 600) {
            currentTime = Date().time
            api.subscribeToDeals {
                if (it.isNotEmpty() && (newDeals.isEmpty() || newDeals[0] != it[0])) {
                    newDeals = it
                    isUpdate = true
                    newDealsTime = currentTime
                }
            }
            if (isUpdate) {
                emit(newDeals)
                isUpdate = false
            }
            delay(1000)
        }
    }
}