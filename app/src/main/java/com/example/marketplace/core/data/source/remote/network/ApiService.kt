package com.example.marketplace.core.data.source.remote.network

import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.core.data.source.remote.response.BaseListResponse
import com.example.marketplace.core.data.source.remote.response.BaseSingleResponse
import com.example.marketplace.core.data.source.remote.response.LoginResponse
import com.example.marketplace.core.data.source.remote.response.TokoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService  {

//    API Login
    @POST("login")
    suspend fun login(
        @Body login: LoginRequest
    ): Response<LoginResponse>

//    API Register
    @POST("register")
    suspend fun register(
        @Body data: RegisterRequest
    ): Response<LoginResponse>

//    API update profil
    @PUT("update-user/{id}")
    suspend fun updateUser(
        @Path("id") int: Int,
        @Body data: UpdateProfileRequest
    ): Response<LoginResponse>

//    API upload foto profil
    @Multipart
    @POST("upload-user/{id}")
    suspend fun uploadUser(
        @Path("id") int: Int? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<LoginResponse>

//    API Buat Toko
    @POST("toko")
    suspend fun createToko(
        @Body data: CreateTokoRequest
    ): Response<BaseSingleResponse<TokoResponse>>

//    API cek toko
    @GET("toko-user/{id}")
    suspend fun getUser(
        @Path("id") int: Int? = null,
    ): Response<LoginResponse>

//    API mendapatkan alamat
    @GET("alamat-toko/{id}")
    suspend fun getAlamatToko(
        @Path("id") idToko: Int? = null,
    ): Response<BaseListResponse<AlamatToko>>

//    API Buat alamat Toko
    @POST("alamat-toko")
    suspend fun createAlamatToko(
        @Body data: AlamatToko
    ): Response<BaseSingleResponse<AlamatToko>>

//    API ubah alamat Toko
    @PUT("alamat-toko/{id}")
    suspend fun updateAlamatToko(
        @Path("id") id: Int? = null,
        @Body data: AlamatToko,
    ): Response<BaseSingleResponse<AlamatToko>>
}