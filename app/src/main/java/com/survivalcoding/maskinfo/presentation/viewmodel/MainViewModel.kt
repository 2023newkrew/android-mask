package com.survivalcoding.maskinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.mapper.toMaskStock
import com.survivalcoding.maskinfo.domain.repository.StoreRepository
import com.survivalcoding.maskinfo.presentation.ui_state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val storeRepository: StoreRepository) : ViewModel() {
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