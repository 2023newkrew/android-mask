package com.survivalcoding.maskinfo.domain.use_case

import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository

class GetPhotosUseCase(
    private val repository: PhotoRepository
) {

    suspend operator fun invoke(query: String): Result<List<Photo>> {
        return repository.getPhotos(query)
    }

}