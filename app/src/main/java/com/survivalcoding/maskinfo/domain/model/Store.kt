package com.survivalcoding.maskinfo.domain.model

data class Store(
    val address: String,
    val location: Location,
    val name: String,
    val remainStatus: String,
    val distance: Double?,
)