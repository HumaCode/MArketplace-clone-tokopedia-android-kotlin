package com.example.marketplace.ui.alamatToko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.databinding.ActivityTambahAlamatTokoBinding
import com.example.marketplace.util.defaultError
import com.example.marketplace.util.getTokoId
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditAlamatTokoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null

    private var alamat = AlamatToko() // panggil model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTambahAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar
        setToolbar(binding.lyToolbar.toolbar, "Edit Alamat Toko")

        getExtra()
        mainButton()
        setupUi()
    }

//    function mendapatkan data alamat yang mau di edit
    private fun getExtra(){
        val extra: String? = getStringExtra()
        alamat = extra.toModel(AlamatToko::class.java) ?: AlamatToko()

        binding.apply {
            edtLabel.setText(alamat.label ?: "Kantor")
            edtAlamat.setText(alamat.alamat)
            edtKodepos.setText(alamat.kodepos)
            edtEmail.setText(alamat.email)
            edtPhone.setText(alamat.phone)
        }
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

//        ketika di edit
        binding.apply {
            val indexProv = listProvinsi.indexOfFirst { it == alamat.provinsi }
            spnProvinsi.setSelection(indexProv)
        }

        binding.apply {
            val indexKota = listKota.indexOfFirst { it == alamat.kota }
            spnKota.setSelection(indexKota)
        }

        binding.apply {
            val indexKec = listKecamatan.indexOfFirst { it == alamat.kecamatan }
            spnKecamatan.setSelection(indexKec)
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
            id = alamat.id,
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
        viewModel.update(reqData).observe(this) {
            when (it.state) {

                State.SUCCESS -> {
                    binding.pb.visibility = View.GONE

                    toastSuccess("Alamat berhasil diubah")
                    onBackPressed()
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


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}