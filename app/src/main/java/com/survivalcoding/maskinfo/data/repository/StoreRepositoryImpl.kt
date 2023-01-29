package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.datasource.MaskDataSource
import com.survivalcoding.maskinfo.data.dto.Store
import com.survivalcoding.maskinfo.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class StoreRepositoryImpl @Inject constructor() {

    @Inject
    lateinit var maskDataSource: MaskDataSource

    suspend fun maskStores(): List<Store> {
        return maskDataSource.getAllResultMaskStock().stores ?: emptyList()
    }
}