package com.survivalcoding.maskinfo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.survivalcoding.maskinfo.data.model.Store
import kotlinx.coroutines.flow.Flow

class StoreRepository(private val storeService: StoreService) {
    fun getPagingStore(): Flow<PagingData<Store>> {
        return Pager(PagingConfig(pageSize = 20)) {
            StorePagingSource(storeService)
        }.flow
    }
}