package com.survivalcoding.maskinfo.domain.use_case

import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository
import com.survivalcoding.maskinfo.domain.util.MyResult

class GetPhotosUseCase(
    private val repository: PhotoRepository
) {

    suspend operator fun invoke(query: String): MyResult<List<Photo>> {
        return repository.getPhotos(query)
    }

}