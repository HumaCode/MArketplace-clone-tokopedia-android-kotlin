package com.example.marketplace.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.LoginRequest

class LoginViewModel(val repo: AppRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini halaman Login"
    }
    val text: LiveData<String> = _text

    fun ubahData(){
        _text.postValue("Data ini sudah saya rubah")
    }

//    function login
    fun login(data: LoginRequest) = repo.login(data).asLiveData()
}