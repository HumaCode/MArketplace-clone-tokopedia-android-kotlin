package com.example.marketplace.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest

class NavViewModel(val repo: AppRepository): ViewModel() {

//    function cek toko
    fun getUser(id: Int) = repo.getUser(id).asLiveData()

}