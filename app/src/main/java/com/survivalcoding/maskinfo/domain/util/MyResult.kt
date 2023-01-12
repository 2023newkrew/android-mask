package com.survivalcoding.maskinfo.domain.util

sealed class MyResult<T> {
    class Success<T>(val data: T) : MyResult<T>()
    class Error<T>(val e: Throwable) : MyResult<T>()
}