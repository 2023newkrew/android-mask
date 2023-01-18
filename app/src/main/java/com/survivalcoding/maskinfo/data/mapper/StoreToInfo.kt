package com.survivalcoding.maskinfo.data.mapper

import com.survivalcoding.maskinfo.domain.model.Coordinate
import com.survivalcoding.maskinfo.domain.model.Info
import com.survivalcoding.maskinfo.data.dto.Store

fun Store.toInfo(userCoordinate: Coordinate?): Info? {
    return if (
        name == null ||
        addr == null ||
        remain_stat == null ||
        remain_stat == "break" ||
        remain_stat == "empty"
    ) null
    else Info(
        name = name,
        address = addr,
        stock = remain_stat,
        coordinate = Coordinate(lat ?: 0.0, lng ?: 0.0),
        userCoordinate = userCoordinate
    )
}