package com.survivalcoding.maskinfo.domain.repository

import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.util.MyResult

interface PhotoRepository {

    suspend fun getPhotos(query: String): MyResult<List<Photo>>
}