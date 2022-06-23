package com.example.marketplace.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Prefs(activity: Activity) {
    private var sp : SharedPreferences? = null
    private var login = "login"

//    method yang pertamakali di panggil
    init {
        sp = activity.getSharedPreferences("MYPREF", Context.MODE_PRIVATE)
    }

//    function status login
    fun setIsLogin(value: Boolean) {
        sp!!.edit().putBoolean(login, value).apply()
    }

//    function  status login default false
    fun getIsLogin() : Boolean {
        return sp!!.getBoolean(login, false)
    }

}