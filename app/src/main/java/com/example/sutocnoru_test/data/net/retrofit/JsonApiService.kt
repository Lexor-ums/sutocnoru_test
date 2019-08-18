package com.example.sutocnoru_test.data.net.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface JsonApiService {

    @GET("1ayc1f")
    fun getActualAsync(): Deferred<Response<List<RatingResponse>>>

}