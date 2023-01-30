package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.remote.dto.Store

interface StoreRepository {
    suspend fun maskStores(): List<Store>
}