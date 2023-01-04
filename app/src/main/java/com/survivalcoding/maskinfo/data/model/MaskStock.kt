package com.survivalcoding.maskinfo.data.model

data class MaskStock(
    val place: String,
    val address: String,
    val distance: Double,
    val status: MaskStockStatus
)