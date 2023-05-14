package com.ntpro.mobileandroiddevtestwork.presentation.deals_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntpro.mobileandroiddevtestwork.domain.DealUseCase
import com.ntpro.mobileandroiddevtestwork.data.repository.DealRepository
import com.ntpro.mobileandroiddevtestwork.utils.FiltrationOn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DealsScreenViewModel @Inject constructor(
    private val dealUseCase: DealUseCase,
    private val repository: DealRepository
) : ViewModel() {

    val isFilterAscending = mutableStateOf(false)
    val filtrationOn = mutableStateOf(FiltrationOn.TIME)

    var pager = repository.getFlowByTime(isFilterAscending.value)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            clearDeals().join()
            listenAndAdd()
        }
    }

    private fun listenAndAdd() = viewModelScope.launch(Dispatchers.IO) {
        dealUseCase().collect {
            repository.createDeals(it)
        }
    }

    private fun clearDeals() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllDeals()
    }

    fun updateDealsFiltration() {
        pager = when(filtrationOn.value) {
            FiltrationOn.TIME -> repository.getFlowByTime(isFilterAscending.value)
            FiltrationOn.INSTRUMENT -> repository.getFlowByInstrumentName(isFilterAscending.value)
            FiltrationOn.PRICE -> repository.getFlowByPrice(isFilterAscending.value)
            FiltrationOn.AMOUNT -> repository.getFlowByAmount(isFilterAscending.value)
            FiltrationOn.SIDE -> repository.getFlowBySide(isFilterAscending.value)
        }
    }
}