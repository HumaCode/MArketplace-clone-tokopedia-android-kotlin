package com.example.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.databinding.ActivityTambatAlamatTokoBinding
import com.example.marketplace.util.defaultError
import com.example.marketplace.util.getTokoId
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambatAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambatAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.lyToolbar.toolbar, "Tambah Alamat Baru")

        mainButton()
        setupUi()
    }

    private fun setupUi() {
        val listProvinsi = listOf("Pilih Provinsi", "Tangerang", "Jakarta Timur", "Jakarta Barat", "Jakarta Selatan")
        val listKota = listOf("Pilih Kota", "Bekasi", "Pekalongan", "Semarang", "Tegal")
        val listKecamatan = listOf("Pilih Kecamatan", "Kajen", "Kesesi", "Karanganyar", "Talun")

//        masukan ke dalam spiner
        binding.spnProvinsi.setOnPositionSelectedListener(this, listProvinsi) {
            if(it == 0) {
                provinsiId = null
            }else{
                provinsiId = 10    // id provinsi di rajaongkir
                provinsi = listProvinsi[it]
            }
        }

        binding.spnKota.setOnPositionSelectedListener(this, listKota) {
            if(it == 0) {
                kotaId = null
            }else{
                kotaId = 399    // id kota di rajaongkir
                kota = listKota[it]
            }
        }

        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan) {
            if(it == 0) {
                kecamatanId = null
            }else{
                kecamatanId = 5505    // id kecamatan di rajaongkir
                kecamatan = listKecamatan[it]
            }
        }

    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if(validate()) simpan()
            }

            lyToolbar.btnSimpan.setOnLongClickListener {
//                data dummy otomatis
                edtLabel.setText("Kantor")
                edtAlamat.setText("Jl, Alamat Kantor No. 3")
                edtKodepos.setText("11223")
                edtEmail.setText("kantor@gmail.com")
                edtPhone.setText("092277261")

                return@setOnLongClickListener true
            }
        }
    }

//    validasi inputan
    private fun validate(): Boolean {
        binding.apply {
            if(edtLabel.isEmpty()) return false
            if(edtAlamat.isEmpty()) return false
            if(edtKodepos.isEmpty()) return false
            if(edtEmail.isEmpty()) return false
            if(edtPhone.isEmpty()) return false
            if(provinsiId == null) {
                toastSimple("Provinsi tidak boleh kosong")
                return false
            }
            if(kotaId == null) {
                toastSimple("Kota tidak boleh kosong")
                return false
            }
            if(kecamatanId == null) {
                toastSimple("Kecamatan tidak boleh kosong")
                return false
            }
        }

        return true
    }

//    simpan alamat
    private fun simpan() {

//    ambil data yang di perlukan
        val reqData = AlamatToko(
            tokoId = getTokoId(),
            label = binding.edtLabel.getString(),
            alamat = binding.edtAlamat.getString(),
            provinsi = provinsi,
            kota = kota,
            kecamatan = kecamatan,
            provinsiId = provinsiId,
            kecamatanId = kecamatanId,
            kotaId = kotaId,
            email = binding.edtEmail.getString(),
            kodepos = binding.edtKodepos.getString(),
            phone = binding.edtPhone.getString(),
        )
        viewModel.create(reqData).observe(this) {
            when (it.state) {

                State.SUCCESS -> {
//                    dismisLoading()
                    toastSuccess("Alamat berhasil ditambahkan")
                    onBackPressed()
                }

                State.ERROR -> {
//                    dismisLoading()
                    showErrorDialog(it.message.defaultError())
                }

                State.LOADING -> {
//                    showLoading()
                }

            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}