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
    var myLat: Float = 37.394940f
    var myLng: Float = 127.11010f

    private var _maskStocks = MutableLiveData<List<MaskStock>>()
    val maskStocks: LiveData<List<MaskStock>> = _maskStocks

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            _maskStocks.postValue(storeRepository.maskStores().mapNotNull { it.toMaskStock(myLat, myLng) }.sortedBy { it.distance })
        }
    }

}