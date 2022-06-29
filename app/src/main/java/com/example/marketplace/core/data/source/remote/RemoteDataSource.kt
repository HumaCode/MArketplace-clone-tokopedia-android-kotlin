package com.example.marketplace.core.data.source.remote

import com.example.marketplace.core.data.source.remote.network.ApiService
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class RemoteDataSource(private val api: ApiService) {
//    login
    suspend fun login(data: LoginRequest) = api.login(data)

//    register
    suspend fun register(data: RegisterRequest) = api.register(data)

//    update profil
    suspend fun updateUser(data: UpdateProfileRequest) = api.updateUser(data.id, data)

//    upload profil
    suspend fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploadUser(id, fileImage)

//    buat toko
    suspend fun createToko(data: CreateTokoRequest) = api.createToko(data)

//    cek toko
    suspend fun getUser(id: Int? = null) = api.getUser(id)
}