package com.example.marketplace.core.data.repository


import com.example.marketplace.core.data.source.local.LocalDataSource
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.RemoteDataSource
import com.example.marketplace.core.data.source.remote.network.Resource
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest
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
            emit(Resource.error(e.message?: "Upload gagal", null))
        }
    }

 //    function buat Toko
    fun createToko(data: CreateTokoRequest) = flow {
//        state loading
        emit(Resource.loading(null))

            try {
                remote.createToko(data).let {
                    if(it.isSuccessful) {

                        val body = it.body()?.data  // menampng data toko
//                Prefs.setUser(user)  // mengambil data toko
                        emit(Resource.success(body))  // success
                    }else{
                        emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    }
                }
            }catch (e:Exception){
                emit(Resource.error(e.message?: "Gagal membuat toko", null))
            }
        }


//    function cek toko user
    fun getUser(id: Int? = null) = flow {
//        state loading
        emit(Resource.loading(null))

        try {
            remote.getUser(id).let {
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
            emit(Resource.error(e.message?: "User tidak ditemukan", null))
        }
    }

//    function menampilkan alamat toko user
    fun getAlamatToko() = flow {
//        state loading
            emit(Resource.loading(null))

            try {
                remote.getAlamatToko().let {
                    if(it.isSuccessful) {
                        val body = it.body()
                        val data = body?.data  // menampng data alamat toko
                        emit(Resource.success(data))  // success
                    }else{
                        emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                    }
                }
            }catch (e:Exception){
                emit(Resource.error(e.message?: "Alamat toko tidak ditemukan", null))
            }
        }

    //    function buat Toko
    fun createAlamatToko(data: AlamatToko) = flow {
//        state loading
        emit(Resource.loading(null))

        try {
            remote.createAlamatToko(data).let {
                if(it.isSuccessful) {

                    val body = it.body()?.data  // menampng data toko
                    //                Prefs.setUser(user)  // mengambil data toko
                    emit(Resource.success(body))  // success
                }else{
                    emit(Resource.error(it.getErrorBody()?.message ?: "Error default", null))
                }
            }
        }catch (e:Exception){
            emit(Resource.error(e.message?: "Gagal membuat toko", null))
        }
    }

}