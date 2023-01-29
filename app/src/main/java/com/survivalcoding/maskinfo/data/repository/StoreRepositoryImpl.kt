package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.datasource.MaskDataSource
import com.survivalcoding.maskinfo.data.dto.Store

class StoreRepositoryImpl {
    private val maskDataSource: MaskDataSource = MaskDataSource.create()
    suspend fun maskStores(): List<Store> {
        return maskDataSource.getAllResultMaskStock().stores ?: emptyList()
    }
}