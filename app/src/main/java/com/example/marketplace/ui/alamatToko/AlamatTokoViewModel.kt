package com.example.marketplace.ui.alamatToko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.marketplace.core.data.repository.AppRepository
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest

class AlamatTokoViewModel(private val repo: AppRepository): ViewModel() {

//    function menampilkan alamat toko
    fun get() = repo.getAlamatToko().asLiveData()

//    function membuat alamat toko
    fun create(data: AlamatToko) = repo.createAlamatToko(data).asLiveData()

}