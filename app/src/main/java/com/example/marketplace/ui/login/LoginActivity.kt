package com.example.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.NavigationActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.databinding.ActivityLoginBinding
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

//    panggil viewModelLogin
    private val viewModel : LoginViewModel by viewModel()


    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    setData()

    }

    private fun setData(){
        viewModel.text.observe(this) {
            binding.edtEmail.setText(it)
        }

        binding.btnMasuk.setOnClickListener {
            login()
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
                    dismisLoading()
                    showToast("Selamat datang "+ it.data?.name)

//                    arahkan ke navigation activity
                    pushActivity(NavigationActivity::class.java)
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