package com.example.marketplace.core.data.source.remote.network

import com.example.marketplace.core.data.source.remote.request.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService  {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
    ): Response<ResponseBody>

    @POST("register")
    suspend fun register(
    ): Response<ResponseBody>
}