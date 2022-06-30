package com.example.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        getData()
        setupAdapter()
        setupUi()
    }

//    function update otomatis
    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun setupUi() {
        binding.apply {
            lyToolbar.btnTambah.apply {
                lyToolbar.btnTambah.toVisible()
                setOnClickListener {
                    intentActivity(TambahAlamatTokoActivity::class.java)
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rvData.adapter = adapter
    }

    private fun getData() {
        viewModel.get().observe(this) {
            when(it.state) {

                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE

                    binding.tvError.toGone()

//                    ambil data alamat toko
                    val data = it.data ?: emptyList()

                    adapter.addItems(data)

                    if(data.isEmpty()) {
                        binding.tvError.toVisible()
                    }

                }

                State.ERROR -> {
                    binding.pb.visibility = View.GONE

                    binding.tvError.toVisible()
                }

                State.LOADING -> {

                    binding.pb.visibility = View.VISIBLE

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