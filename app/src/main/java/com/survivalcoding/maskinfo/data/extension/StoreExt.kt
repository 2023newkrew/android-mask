package com.survivalcoding.maskinfo.data.extension

import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.data.model.Store
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun Store.statusString(): String = when (remain_stat) {
    "few" -> "소진임박"
    "some" -> "여유"
    "plenty" -> "충분"
    else -> throw IllegalArgumentException()
}

fun Store.statusColor(): Int = when (remain_stat) {
    "few" -> R.color.insufficient_red
    "some" -> R.color.spare_yellow
    "plenty" -> R.color.sufficient_green
    else -> throw IllegalArgumentException()
}

fun Store.amountString(): String = when (remain_stat) {
    "few" -> "30개 미만"
    "some" -> "30개 이상"
    "plenty" -> "100개 이상"
    else -> throw IllegalArgumentException()
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
