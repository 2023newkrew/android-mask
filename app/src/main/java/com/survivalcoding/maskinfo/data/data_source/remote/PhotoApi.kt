package com.survivalcoding.maskinfo.data.data_source.remote

import com.survivalcoding.maskinfo.data.data_source.remote.dto.PhotoResult
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {
    companion object {
        const val BASE_URL = "https://pixabay.com/api/"
    }

    @GET("?key=10711147-dc41758b93b263957026bdadb&image_type=photo")
    suspend fun getPhotos(
        @Query("q") query: String
    ): PhotoResult
}