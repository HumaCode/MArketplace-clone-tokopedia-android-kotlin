package com.example.marketplace.util

import com.chibatching.kotpref.KotprefModel
import com.example.marketplace.core.data.source.model.User
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Prefs : KotprefModel(){
    var isLogin by booleanPref(false)

    var user by stringPref()

//    mengubah data user dari json ke string
    fun setUser(data: User?) {
        user = data.toJson()
    }

//    ubah menjadi bentuk model
    fun getUser() : User? {
        if(user.isEmpty()) return null
        return user.toModel(User::class.java)
    }
}