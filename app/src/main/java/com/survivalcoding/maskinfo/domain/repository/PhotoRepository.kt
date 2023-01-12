package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.domain.model.Photo

interface PhotoRepository {

    suspend fun getPhotos(query: String): Result<List<Photo>>
}