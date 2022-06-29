package com.example.marketplace.core.di

import com.example.marketplace.ui.auth.AuthViewModel
import com.example.marketplace.ui.navigation.NavViewModel
import com.example.marketplace.ui.toko.TokoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

//    daftarkan view model dari login
    viewModel { AuthViewModel(get()) }

//    daftarkan view model dari login
    viewModel { TokoViewModel(get()) }

//    daftarkan view model dari login
    viewModel { NavViewModel(get()) }
}