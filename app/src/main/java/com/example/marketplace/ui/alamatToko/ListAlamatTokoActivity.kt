package com.example.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.databinding.ActivityAlamatTokoListBinding
import com.example.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlamatTokoListBinding
    private val viewModel: AlamatTokoViewModel  by viewModel()  // panggil model alamat
    private val adapter = AlamatTokoAdapter() // panggil adapter alamat toko

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlamatTokoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.lyToolbar.toolbar, "Daftar Alamat")

        mainButton()
        observer()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun observer() {
        viewModel.get().observe(this) {
            when(it.state) {

                State.SUCCESS -> {
                    binding.tvError.toGone()

//                    ambil data alamat toko
                    val data = it.data ?: emptyList()

                    adapter.addItems(data)

                    if(data.isEmpty()) {
                        binding.tvError.toVisible()
                    }

                }

                State.ERROR -> {
                    binding.tvError.toVisible()
                }

                State.LOADING -> {

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