package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.data.data_source.remote.dto.PhotoResult

interface PhotoRepository {

    suspend fun getPhotos(query: String): PhotoResult
}