package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.data_source.dto.Mask
import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.StoreInfo

interface InfoRepository {
    suspend fun getMask(currentPage: Int, userCoordinate: Coordinate?): List<StoreInfo?>
}