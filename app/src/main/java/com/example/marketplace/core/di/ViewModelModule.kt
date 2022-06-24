package com.example.marketplace.core.di

import com.example.marketplace.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

//    panggil view model dari login
    viewModel { AuthViewModel(get()) }
}