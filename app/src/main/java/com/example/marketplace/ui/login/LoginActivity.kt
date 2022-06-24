package com.example.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.core.data.source.remote.request.LoginRequest
import com.example.marketplace.databinding.ActivityLoginBinding
import com.example.marketplace.util.Prefs
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

//            tangkap inputan
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val body = LoginRequest(email, password)

//          panggil function login
            viewModel.login(body).observe(this) {

            }
        }
    }
}