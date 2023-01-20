package com.survivalcoding.maskinfo

import android.app.Application
import com.survivalcoding.maskinfo.data.repository.StoreRepositoryImpl
import com.survivalcoding.maskinfo.data.datasource.MaskDataSource

class MaskInfoApplication : Application() {
    val storeRepositoryImpl = StoreRepositoryImpl(MaskDataSource.create())
}