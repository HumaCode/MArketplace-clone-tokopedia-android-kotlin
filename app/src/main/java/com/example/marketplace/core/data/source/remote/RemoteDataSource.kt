package com.example.marketplace.core.data.source.remote

import com.example.marketplace.core.data.source.remote.network.ApiService
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest

class RemoteDataSource(private val api: ApiService) {
//    login
    suspend fun login(data: LoginRequest) = api.login(data)

//    register
    suspend fun register(data: RegisterRequest) = api.register(data)

}