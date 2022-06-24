package com.example.marketplace.core.data.repository


import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun login(data: LoginRequest) = flow {
        try {
            remote.login(data).let {
                if(it.isSuccessful) {

                    val body = it.body()
                    emit(body)
                    logs("Success : " + body.toString())
                }else{
                    logs("Error : " + "keterangan error")
                }
            }
        }catch (e:Exception){
            logs("Login gagal :" + e.message)
        }
    }

}