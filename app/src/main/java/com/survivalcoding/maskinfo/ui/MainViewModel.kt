package com.survivalcoding.maskinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.survivalcoding.maskinfo.MaskInfoApplication
import com.survivalcoding.maskinfo.data.StoreRepository

class MainViewModel(
    private val storeRepository: StoreRepository
) : ViewModel() {
    var myLat: Double = 37.394940490775 //판교역 위도
    var myLng: Double = 127.110105738

    val pagingStores = storeRepository.getPagingStore().cachedIn(viewModelScope)

}

class MainViewModelFactory(private val application: MaskInfoApplication) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(application.storeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}