package com.example.marketplace.core.di

import com.example.marketplace.core.data.repository.AppRepository
import org.koin.dsl.module

var repositoryModule = module {

//    panggil semua repositori baik lokal maupun remote
    single { AppRepository(get(), get()) }
}