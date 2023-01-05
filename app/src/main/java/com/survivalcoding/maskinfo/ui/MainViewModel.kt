package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.maskinfo.data.StoreRepository
import com.survivalcoding.maskinfo.data.model.MaskStock
import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.remote.mapper.toMaskStock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val storeRepository = StoreRepository(StoreService.create())

    private var _maskStocks = MutableLiveData<List<MaskStock>>()
    val maskStocks: LiveData<List<MaskStock>> = _maskStocks

    fun load(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _maskStocks.postValue(storeRepository.maskStores(page).mapNotNull { it.toMaskStock() })
        }
    }

}