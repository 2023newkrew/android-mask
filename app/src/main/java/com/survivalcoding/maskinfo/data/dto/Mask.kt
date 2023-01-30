package com.survivalcoding.maskinfo.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Mask(
    val count: Int,
    val stores: List<Store>
)