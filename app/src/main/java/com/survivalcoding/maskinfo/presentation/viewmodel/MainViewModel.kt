package com.survivalcoding.maskinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.mapper.toMaskStock
import com.survivalcoding.maskinfo.domain.repository.StoreRepository
import com.survivalcoding.maskinfo.presentation.ui_state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val storeRepository: StoreRepository) : ViewModel() {
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

class MainViewModelFactory(private val storeRepository: StoreRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(storeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}