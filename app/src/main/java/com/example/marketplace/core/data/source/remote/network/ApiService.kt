package com.example.marketplace.core.data.source.remote.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService  {

    @POST("login")
    suspend fun login(
//        @Body
//        login: LoginRequest,
    ): Response<ResponseBody>
}