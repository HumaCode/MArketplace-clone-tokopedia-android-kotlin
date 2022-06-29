package com.example.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.NavigationActivity
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.example.marketplace.databinding.ActivityBukaTokoBinding
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BukaTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel  by viewModel()  // panggil tokoviewmodel / model toko

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.lyToolbar.toolbar, "Buat Toko Baru")

        mainButton()
    }

    private fun mainButton() {
        binding.btnBukaToko.setOnClickListener {
            bukaToko()
        }
    }

    private fun bukaToko() {
//        data yang akan dimasukan
        val body = CreateTokoRequest(
            userId = Prefs.getUser()?.id ?: 0,
            name = binding.edtNamaToko.getString(),
            kota = binding.edtLokasi.getString()
        )

//          panggil function buat toko
        viewModel.createToko(body).observe(this) {
//            tampilkan pesan menggunakan state
            when (it.state) {
//                jika success
                State.SUCCESS -> {

                    val data = it.data

//                    dismisLoading()
                    showToast("Selamat toko " + data?.name + " berhasil dibuat")

//                    arahkan ke toko saya activity
                    intentActivity(TokoSayaActivity::class.java)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}