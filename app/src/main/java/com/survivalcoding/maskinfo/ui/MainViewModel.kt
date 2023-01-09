package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.StoreRepository
import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.remote.mapper.toMaskStock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val storeRepository = StoreRepository(StoreService.create())
    var myLat: Float = 37.394940f
    var myLng: Float = 127.11010f

    private var _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState = _mainUiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _mainUiState.value = mainUiState.value.copy(
                maskStocks = storeRepository.maskStores()
                    .mapNotNull { it.toMaskStock(myLat, myLng) }.sortedBy { it.distance }
            )
        }
    }

}

data class MainUiState(
    val maskStocks: List<MaskStock> = emptyList()
)