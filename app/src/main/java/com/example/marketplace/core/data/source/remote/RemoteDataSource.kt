package com.example.marketplace.core.data.source.remote

import com.example.marketplace.core.data.source.remote.network.ApiService
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest

class RemoteDataSource(private val api: ApiService) {
//    login
    suspend fun login(data: LoginRequest) = api.login(data)

//    register
    suspend fun register(data: RegisterRequest) = api.register(data)

//    update profil
    suspend fun updateUser(data: UpdateProfileRequest) = api.updateUser(data.id, data)

}