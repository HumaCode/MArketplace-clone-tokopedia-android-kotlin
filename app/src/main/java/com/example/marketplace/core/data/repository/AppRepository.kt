package com.example.marketplace.core.data.repository


import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import com.example.marketplace.core.data.source.remote.network.Resource
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

//    function login
    fun login(data: LoginRequest) = flow {
//        state loading
        emit(Resource.loading(null))

        try {
            remote.login(data).let {
                if(it.isSuccessful) {
                    Prefs.isLogin = true  // ubah status login menjadi true
                    val body = it.body()
                    val user = body?.data  // menampng data user login
                    Prefs.setUser(user)  // mengambil data user
                    emit(Resource.success(user))  // success
                    logs("Success : " + body.toString())
                }else{
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    logs("Error : " + "keterangan error")
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?: "Login gagal", null))
            logs("Login gagal :" + e.message)
        }
    }

//    function register
    fun register(data: RegisterRequest) = flow {
//        state loading
    emit(Resource.loading(null))

    try {
        remote.register(data).let {
            if(it.isSuccessful) {
//                Prefs.isLogin = false  // ubah status login menjadi false
                val body = it.body()
                val user = body?.data  // menampng data user login
//                Prefs.setUser(user)  // mengambil data user
                emit(Resource.success(user))  // success
                logs("Success : " + body.toString())
            }else{
                emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                logs("Error : " + "keterangan error")
            }
        }
    }catch (e:Exception){
        emit(Resource.error(e.message?: "Login gagal", null))
        logs("Login gagal :" + e.message)
    }
}

//    function update profil
    fun updateUser(data: UpdateProfileRequest) = flow {
//        state loading
        emit(Resource.loading(null))

        try {
            remote.updateUser(data).let {
                if(it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data  // menampng data user login
                    Prefs.setUser(user)  // mengambil data user
                    emit(Resource.success(user))  // success
                }else{
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?: "Login gagal", null))
        }
    }

    //    function upload foto user
    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = flow {
//        state loading
        emit(Resource.loading(null))

        try {
            remote.uploadUser(id, fileImage).let {
                if(it.isSuccessful) {
                    val body = it.body()
                    val user = body?.data  // menampng data user login
                    Prefs.setUser(user)  // mengambil data user
                    emit(Resource.success(user))  // success
                }else{
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?: "Login gagal", null))
        }
    }
}