package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.data_source.remote.PhotoApi
import com.survivalcoding.maskinfo.data.data_source.remote.mapper.toPhoto
import com.survivalcoding.maskinfo.domain.model.Photo
import com.survivalcoding.maskinfo.domain.repository.PhotoRepository
import com.survivalcoding.maskinfo.domain.util.MyResult

class PhotoRepositoryImpl(
    private val api: PhotoApi
) : PhotoRepository {

    override suspend fun getPhotos(query: String): MyResult<List<Photo>> {
        try {
            val response = api.getPhotos(query = query)

            return if (response.isSuccessful) {
                val photos = response.body()?.hits?.map { it.toPhoto() } ?: emptyList()
                MyResult.Success(photos)
            } else {
                when (response.code()) {
                    400 -> MyResult.Error(PhotoException.BadRequestException("리퀘스트 잘못"))
                    500 -> MyResult.Error(PhotoException.ServerException("서버 먹통"))
                    else -> MyResult.Error(PhotoException.Exception("실패"))
                }
            }
        } catch (e: Exception) {
            return MyResult.Error(PhotoException.NetworkException("네트워크 에러"))
        }
    }
}

sealed class PhotoException(message: String) : Exception(message) {
    class ServerException(message: String) : PhotoException(message)
    class BadRequestException(message: String) : PhotoException(message)
    class NetworkException(message: String) : PhotoException(message)
    class Exception(message: String) : PhotoException(message)
}
