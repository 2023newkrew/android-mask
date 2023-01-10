package com.survivalcoding.maskinfo

import android.app.Application
import com.survivalcoding.maskinfo.data.StoreRepository
import com.survivalcoding.maskinfo.data.remote.StoreService

class MaskInfoApplication : Application() {
    val storeRepository = StoreRepository(StoreService.create())
}