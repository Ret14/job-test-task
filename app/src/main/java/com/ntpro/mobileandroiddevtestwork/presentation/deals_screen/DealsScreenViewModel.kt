package com.ntpro.mobileandroiddevtestwork.presentation.deals_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import com.ntpro.mobileandroiddevtestwork.Server
import com.ntpro.mobileandroiddevtestwork.domain.DealUseCase
import com.ntpro.mobileandroiddevtestwork.data.repository.DealRepository
import com.ntpro.mobileandroiddevtestwork.data.room.DealsDatabase
import com.ntpro.mobileandroiddevtestwork.data.room.entities.LocalDeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class DealsScreenViewModel @Inject constructor(
    private val database: DealsDatabase,
    private val dealUseCase: DealUseCase,
    private val repository: DealRepository
) : ViewModel() {

    val isFilterAscending = mutableStateOf(true)

    val pager = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = true),
        pagingSourceFactory = { database.deals.readAllByPrice(true) }
    ).flow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            clearDeals().join()
            listenAndAdd()
        }
    }

    private fun listenAndAdd() = viewModelScope.launch(Dispatchers.IO) {
        dealUseCase().collect {
            addDeals(it)
        }
    }

    private fun addDeals(deals: List<Server.Deal>) = viewModelScope.launch(Dispatchers.IO) {
        val chunkSize = 50
        val dealsToAdd = deals.chunked(chunkSize)
        dealsToAdd.forEach {
            viewModelScope.launch(Dispatchers.IO) {
                repository.createDeals(it)
            }
        }
    }

    private fun clearDeals() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllDeals()
    }
}