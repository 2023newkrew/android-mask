package com.survivalcoding.maskinfo.data

import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.remote.dto.Store

class StoreRepository(private val storeService: StoreService) {

    suspend fun maskStores(page: Int): List<Store> {
        return storeService.getResultMaskStock(page, 20).stores ?: emptyList()
    }
}