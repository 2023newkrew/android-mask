package com.survivalcoding.maskinfo.data.model

data class Store(
    var addr: String = "",
    var code: String = "",
    var created_at: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var name: String = "",
    var remain_stat: String = "",
    var stock_at: String = "",
    var type: String = "",
    val id: Int = 1
)