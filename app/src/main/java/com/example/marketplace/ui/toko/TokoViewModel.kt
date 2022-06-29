package com.example.marketplace.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest

class TokoViewModel(val repo: AppRepository): ViewModel() {

//    function buat toko
    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()

}