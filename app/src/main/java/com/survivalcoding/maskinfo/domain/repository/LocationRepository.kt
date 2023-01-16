package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.domain.model.Location

interface LocationRepository {
    suspend fun getLocation(): Location

    fun distanceBetween(
        startLocation: Location,
        endLocation: Location,
    ): Double
}