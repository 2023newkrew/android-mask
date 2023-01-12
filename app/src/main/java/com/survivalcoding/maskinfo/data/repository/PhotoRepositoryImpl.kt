package com.survivalcoding.maskinfo.data.repository

import com.survivalcoding.maskinfo.data.data_source.remote.PhotoApi
import com.survivalcoding.maskinfo.data.data_source.remote.dto.PhotoResult
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
                    400 -> MyResult.Error(BadRequestError("리퀘스트 잘못"))
                    500 -> MyResult.Error(ServerError("서버 먹통"))
                    else -> MyResult.Error(Exception("실패"))
                }
            }
        } catch (e: Exception) {
            return MyResult.Error(NetworkError("네트워크 에러"))
        }
    }
}

class ServerError(message: String) : Exception(message)
class BadRequestError(message: String) : Exception(message)
class NetworkError(message: String) : Exception(message)