package com.example.marketplace.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    fun setData(){
        viewModel.text.observe(this) {
            binding.edtEmail.setText(it)
        }

        binding.btnMasuk.setOnClickListener {
            viewModel.ubahData()
        }
    }

    fun testing(){
        //        panggil preverense
//        var s = Prefs(this)
//        if(s.getIsLogin()) {
//            binding.tvStatus.text = "Sudah Login"
//        }else{
//            binding.tvStatus.text = "Belum Login"
//        }

//        tombol login
//        binding.btnMasuk.setOnClickListener {
//            s.setIsLogin(true) // status login true
//            onBackPressed()
//        }

//        tombol logout
//        binding.btnLogout.setOnClickListener {
//            s.setIsLogin(false) // status login false
//            onBackPressed()
//        }
    }


}