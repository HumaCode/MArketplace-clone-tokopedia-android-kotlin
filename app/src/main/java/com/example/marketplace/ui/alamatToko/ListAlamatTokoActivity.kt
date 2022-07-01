package com.example.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.databinding.ActivityAlamatTokoListBinding
import com.example.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.example.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlamatTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlamatTokoListBinding
    private val viewModel: AlamatTokoViewModel  by viewModel()  // panggil model alamat
    private val adapter = AlamatTokoAdapter{ item, pos ->
        confirmDeleteAlamat(item, pos) // confirmasi hapus
    } // panggil adapter alamat toko

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

    private fun confirmDeleteAlamat(item: AlamatToko, pos: Int){
        showConfirmDialog("Hapus Alamat", "Apakah yakin akan menghapus alamat ini.?", "Hapus") {
            onDelete(item, pos) // item dan posisi index
        }
    }

//    function hapus
    private fun onDelete(item: AlamatToko, pos: Int){

        viewModel.delete(item.id).observe(this) {
            when (it.state) {

                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE
                    adapter.removeAt(pos)

                    toastSuccess("Alamat berhasil dihapus")
                }

                State.ERROR -> {
                    binding.pb.visibility = View.GONE

                    showErrorDialog(it.message.defaultError())
                }

                State.LOADING -> {

                    binding.pb.visibility = View.VISIBLE

                }

            }
        }


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