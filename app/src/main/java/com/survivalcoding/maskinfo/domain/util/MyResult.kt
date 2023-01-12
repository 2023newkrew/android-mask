package com.survivalcoding.maskinfo.domain.util

import com.survivalcoding.maskinfo.data.repository.PhotoException

sealed class MyResult<T> {
    class Success<T>(val data: T) : MyResult<T>()
    class Error<T>(val e: PhotoException) : MyResult<T>()
}