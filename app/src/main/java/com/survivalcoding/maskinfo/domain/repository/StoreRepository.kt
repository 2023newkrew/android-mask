package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import com.survivalcoding.maskinfo.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class StoreRepository @Inject constructor(private val storeRepositoryImpl: StoreRepositoryImpl) {
    suspend fun maskStores() = storeRepositoryImpl.maskStores()
}