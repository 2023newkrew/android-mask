package com.survivalcoding.maskinfo.data

import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.remote.dto.Store

class StoreRepository(private val storeService: StoreService) {

    suspend fun maskStores(): List<Store> {
        return storeService.getAllResultMaskStock().stores ?: emptyList()
    }
}