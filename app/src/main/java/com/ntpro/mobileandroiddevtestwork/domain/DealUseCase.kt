package com.ntpro.mobileandroiddevtestwork.domain

import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.data.repository.DealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DealUseCase @Inject constructor(private val api: Server, private val repository: DealRepository) {
    operator fun invoke(): Flow<List<Server.Deal>> = flow {
        var isUpdate = false
        var newDeals: List<Server.Deal> = emptyList()
        while (true) {
            api.subscribeToDeals {
                if (it.isNotEmpty() && (newDeals.isEmpty() || newDeals[0] != it[0])) {
                    newDeals = it
                    isUpdate = true
                }
            }
            if (isUpdate) {
                repository.getDataChannel().send(newDeals)
                isUpdate = false
            }
        }
    }
}