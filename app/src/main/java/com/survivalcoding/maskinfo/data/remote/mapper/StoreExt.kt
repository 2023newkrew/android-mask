package com.survivalcoding.maskinfo.data.remote.mapper

import com.survivalcoding.maskinfo.data.remote.dto.Store
import com.survivalcoding.maskinfo.domain.model.MaskStock
import com.survivalcoding.maskinfo.domain.model.MaskStockStatus
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun Store.toMaskStock(myLat: Float, myLng: Float): MaskStock? {
    if (this.lat == null || this.lng == null) return null
    if (name == null || addr == null || remain_stat == null) return null
    return MaskStock(
        name, addr, distanceTo(myLat, myLng).toInt(), when (remain_stat) {
            "few" -> MaskStockStatus.InSufficient
            "some" -> MaskStockStatus.Spare
            "plenty" -> MaskStockStatus.Sufficient
            else -> return null
        }
    )
}

fun Store.distanceTo(
    lat: Float,
    lng: Float
): Float {
    if (this.lat == null || this.lng == null) throw IllegalArgumentException()
    val earthRadius = 6371000.0f //meters
    val dLat = Math.toRadians((lat - this.lat))
    val dLng = Math.toRadians((lng - this.lng))
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(this.lat)) * cos(
        Math.toRadians(this.lat)
    ) *
            sin(dLng / 2) * sin(dLng / 2)
    val c =
        2 * atan2(sqrt(a), sqrt(1 - a))
    return (earthRadius * c).toFloat()
}