package com.example.marketplace.core.data.repository

import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

}