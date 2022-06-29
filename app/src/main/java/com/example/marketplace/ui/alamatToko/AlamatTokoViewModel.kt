package com.example.marketplace.ui.alamatToko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest

class AlamatTokoViewModel(private val repo: AppRepository): ViewModel() {

//    function buat toko
    fun get() = repo.getAlamatToko().asLiveData()

}