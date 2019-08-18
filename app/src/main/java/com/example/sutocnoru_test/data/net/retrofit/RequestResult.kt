package com.example.sutocnoru_test.data.net.retrofit

sealed class RequestResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : RequestResult<T>()
    data class Error(val exception: Exception) : RequestResult<Nothing>()
}