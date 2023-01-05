package com.survivalcoding.maskinfo.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResult(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)