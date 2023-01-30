package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.remote.StoreService
import com.survivalcoding.maskinfo.data.remote.dto.Store
import com.survivalcoding.maskinfo.domain.repository.StoreRepository

class StoreRepositoryImpl(private val storeService: StoreService) : StoreRepository {
    override suspend fun maskStores(): List<Store> {
        return storeService.getAllResultMaskStock().stores ?: emptyList()
    }
}