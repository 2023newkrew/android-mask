package com.survivalcoding.maskinfo.data.data_source.api

import com.survivalcoding.maskinfo.data.data_source.dto.Mask
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("mask")
    suspend fun getMask(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<Mask>
}