package com.survivalcoding.maskinfo.retrofit

import com.survivalcoding.maskinfo.data.dto.Mask
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("mask")
    suspend fun getMask(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Mask
}