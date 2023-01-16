package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.domain.model.Store

interface StoreRepository {
    suspend fun getStores(): List<Store>
}