package com.survivalcoding.maskinfo.domain.use_case

import com.survivalcoding.maskinfo.data.data_source.remote.mapper.toPhoto
import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository

class GetPhotosUseCase(
    private val repository: PhotoRepository
) {

    suspend operator fun invoke(query: String): List<Photo> {
        return try {
            val result = repository.getPhotos(query)
            result.hits.map { it.toPhoto() }
        } catch (e: Exception) {
            emptyList()
        }
    }

}