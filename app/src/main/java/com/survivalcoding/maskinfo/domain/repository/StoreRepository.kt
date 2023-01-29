package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import javax.inject.Inject

class StoreRepository @Inject constructor(private val storeRepositoryImpl: StoreRepositoryImpl) {
    suspend fun maskStores() = storeRepositoryImpl.maskStores()
}