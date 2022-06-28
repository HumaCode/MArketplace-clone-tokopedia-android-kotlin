package com.example.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.databinding.ActivityBukaTokoBinding
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar

class BukaTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBukaTokoBinding

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
            intentActivity(TokoSayaActivity::class.java)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}