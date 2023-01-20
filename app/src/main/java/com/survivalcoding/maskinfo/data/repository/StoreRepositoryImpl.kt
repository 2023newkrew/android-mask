package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.datasource.MaskDataSource
import com.survivalcoding.maskinfo.data.dto.Store
import com.survivalcoding.maskinfo.domain.repository.StoreRepository

class StoreRepositoryImpl(private val maskDataSource: MaskDataSource) : StoreRepository{

    override suspend fun maskStores(): List<Store> {
        return maskDataSource.getAllResultMaskStock().stores ?: emptyList()
    }
}