package com.example.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.R
import com.example.marketplace.databinding.ActivityLoginBinding
import com.example.marketplace.databinding.FragmentHomeBinding
import com.example.marketplace.util.Prefs

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        panggil preverense
        var s = Prefs(this)
        if(s.getIsLogin()) {
            binding.tvStatus.text = "Sudah Login"
        }else{
            binding.tvStatus.text = "Belum Login"
        }

//        tombol login
        binding.btnLogin.setOnClickListener {
            s.setIsLogin(true) // status login true
            onBackPressed()
        }

//        tombol login
        binding.btnLogout.setOnClickListener {
            s.setIsLogin(false) // status login false
            onBackPressed()
        }

    }
}