package com.example.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.databinding.ActivityTokoSayaBinding
import com.example.marketplace.util.Constants
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class TokoSayaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.lyToolbar.toolbar, "Toko Saya")

        mainButton()
        setData()

    }

    private fun setData() {
        //        ambil data user / untuk di tampilkan
        val user = Prefs.getUser()
        if(user != null) {
            binding.apply {

//                cek toko user
                if(user.toko != null) {
                    tvNamaToko.text = user.toko?.name

                    inisial.text = user.toko?.name.getInitial()

//                menampilkan gambar/foto user
                    Picasso.get().load(Constants.USER_URL + user.toko?.image).into(binding.imageProfile)
                }
            }
        }
    }


    private fun mainButton() {
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}