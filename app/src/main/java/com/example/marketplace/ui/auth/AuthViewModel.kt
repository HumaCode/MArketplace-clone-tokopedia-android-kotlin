package com.example.marketplace.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import okhttp3.MultipartBody

class AuthViewModel(val repo: AppRepository): ViewModel() {

//    function login
    fun login(data: LoginRequest) = repo.login(data).asLiveData()

//    function login
    fun register(data: RegisterRequest) = repo.register(data).asLiveData()

//    function update profil
    fun updateUser(data: UpdateProfileRequest) = repo.updateUser(data).asLiveData()

//    function upload foto profil
    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = repo.uploadUser(id, fileImage).asLiveData()
}