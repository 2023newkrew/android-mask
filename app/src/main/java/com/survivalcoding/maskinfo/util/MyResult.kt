package com.survivalcoding.maskinfo.util


sealed class MyResult<T> {
    class Success<T>(val data: T) : MyResult<T>()
    class Error<T>(val e: PhotoException) : MyResult<T>()
}

sealed class PhotoException(message: String) : Exception(message) {
    class ServerException(message: String) : PhotoException(message)
    class BadRequestException(message: String) : PhotoException(message)
    class NetworkException(message: String) : PhotoException(message)
    class Exception(message: String) : PhotoException(message)
}