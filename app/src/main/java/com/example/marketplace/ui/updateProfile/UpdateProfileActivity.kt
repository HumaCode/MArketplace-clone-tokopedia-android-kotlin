package com.example.marketplace.ui.updateProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.NavigationActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.RegisterRequest
import com.example.marketplace.core.data.source.remote.request.UpdateProfileRequest
import com.example.marketplace.databinding.ActivityRegisterBinding
import com.example.marketplace.databinding.ActivityUpdateProfileBinding
import com.example.marketplace.ui.auth.AuthViewModel
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateProfileActivity : AppCompatActivity() {

//    panggil viewMode
    private val viewModel : AuthViewModel by viewModel()


    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.toolbar, "Edit Profil")

        mainButton()

        setData()

    }

    private fun setData() {
//        ambil data user / untuk di tampilkan
        val user = Prefs.getUser()
        if(user != null) {
            binding.apply {
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtTelp.setText(user.phone)
            }
        }
    }

    private fun mainButton() {
        binding.btnSimpan.setOnClickListener {
            register()
        }
    }

//    function register
    private fun register() {
//    validasi inputan tidak boleh kkosong
        if(binding.edtName.isEmpty()) return
        if(binding.edtEmail.isEmpty()) return
        if(binding.edtTelp.isEmpty()) return


//            tangkap inputan
        val id = Prefs.getUser()?.id
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val phone = binding.edtTelp.text.toString()

        val body = UpdateProfileRequest(id.int(), name, email, phone)

 //          panggil function update profil
        viewModel.updateUser(body).observe(this) {
//            tampilkan pesan menggunakan state
            when (it.state) {
//                jika success
                State.SUCCESS -> {
//                    dismisLoading()
                    showToast("Data berhasil diubah ")

//                    kembalikan ke activity sebelumnya
                    onBackPressed()
                }

//                jika error
                State.ERROR -> {
//                    dismisLoading()
                    toastError(it.message ?: "Upszz error..")
                }

//                jika sedang loading
                State.LOADING -> {
//                    showLoading()  // tampilkan proggress bar
                }
            }



        }
    }

//    tombol back
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}