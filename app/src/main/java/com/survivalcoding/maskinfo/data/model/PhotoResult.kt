package com.survivalcoding.maskinfo.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResult(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)