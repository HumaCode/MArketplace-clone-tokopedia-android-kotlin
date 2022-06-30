package com.example.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marketplace.ui.navigation.NavigationActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.databinding.ActivityLoginBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

//    panggil viewModelLogin
    private val viewModel : AuthViewModel by viewModel()


    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainButton()

    }

//    function berisi tombol
    private fun mainButton() {
//    tombol masuk
        binding.btnMasuk.setOnClickListener {
            login()
        }

//    tombol daftar
        binding.btnDaftar.setOnClickListener {
            intentActivity(RegisterActivity::class.java)
        }
    }

//    function login
    private fun login() {
//    validasi inputan tidak boleh kkosong
        if(binding.edtEmail.isEmpty()) return
        if(binding.edtPassword.isEmpty()) return


//            tangkap inputan
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        val body = LoginRequest(email, password)

 //          panggil function login
        viewModel.login(body).observe(this) {
//            tampilkan pesan menggunakan state
            when (it.state) {
//                jika success
                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE

                    showToast("Selamat datang "+ it.data?.name)

//                    arahkan ke navigation activity
                    pushActivity(NavigationActivity::class.java)
                }

//                jika error
                State.ERROR -> {
                    binding.pb.visibility = View.GONE
                    toastError(it.message ?: "Upszz error..")
                }

//                jika sedang loading
                State.LOADING -> {
                    binding.pb.visibility = View.VISIBLE  // tampilkan proggress bar
                }
            }
        }
    }
}