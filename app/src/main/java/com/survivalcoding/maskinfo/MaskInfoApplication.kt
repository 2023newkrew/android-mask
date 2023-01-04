package com.survivalcoding.maskinfo

import android.app.Application
import com.survivalcoding.maskinfo.data.StoreRepository
import com.survivalcoding.maskinfo.data.StoreService

class MaskInfoApplication : Application() {
    private val storeService by lazy { StoreService.create() }
    val storeRepository by lazy { StoreRepository(storeService) }
}