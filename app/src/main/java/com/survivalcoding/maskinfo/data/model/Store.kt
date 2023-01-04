package com.survivalcoding.maskinfo.data.model

data class Store(
    val addr: String?,
    val code: String?,
    val created_at: String?,
    val lat: Double?,
    val lng: Double?,
    val name: String?,
    val remain_stat: String?,
    val stock_at: String?,
    val type: String?,
) {
    fun isNotNull() =
        null in setOf<Any?>(addr, code, created_at, lat, lng, name, remain_stat, stock_at, type)
}