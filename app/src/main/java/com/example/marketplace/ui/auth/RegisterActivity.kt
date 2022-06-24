package com.example.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

//    panggil viewMode
    private val viewModel : AuthViewModel by viewModel()


    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainButton()

    }

    private fun mainButton() {
        binding.btnDaftar.setOnClickListener {
            register()
        }

        binding.btnLogin.setOnClickListener {
            intentActivity(LoginActivity::class.java)
        }
    }

//    function register
    private fun register() {
//    validasi inputan tidak boleh kkosong
        if(binding.edtName.isEmpty()) return
        if(binding.edtEmail.isEmpty()) return
        if(binding.edtTelp.isEmpty()) return
        if(binding.edtPassword.isEmpty()) return


//            tangkap inputan
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val phone = binding.edtTelp.text.toString()
        val password = binding.edtPassword.text.toString()

        val body = RegisterRequest(name, email, phone, password)

 //          panggil function login
        viewModel.register(body).observe(this) {
//            tampilkan pesan menggunakan state
            when (it.state) {
//                jika success
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Akun berhasil dibuat ")

//                    arahkan ke login activity
                    pushActivity(LoginActivity::class.java)
                }

//                jika error
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Upszz error..")
                }

//                jika sedang loading
                State.LOADING -> {
                    showLoading()  // tampilkan proggress bar
                }
            }



        }
    }
}