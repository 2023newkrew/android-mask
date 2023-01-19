package com.survivalcoding.maskinfo.data.data_source.mapper

import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.StoreInfo
import com.survivalcoding.maskinfo.data.data_source.dto.Store

fun Store.toStoreInfo(userCoordinate: Coordinate?): StoreInfo? {
    return if (
        name == null ||
        addr == null ||
        remain_stat == null ||
        remain_stat == "break" ||
        remain_stat == "empty"
    ) null
    else StoreInfo(
        name = name,
        address = addr,
        stock = remain_stat,
        coordinate = Coordinate(lat ?: 0.0, lng ?: 0.0),
        userCoordinate = userCoordinate
    )
}