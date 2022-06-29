package com.example.marketplace.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marketplace.R
import com.example.marketplace.core.data.source.remote.network.State
import com.example.marketplace.databinding.ActivityNavigationBinding
import com.example.marketplace.ui.auth.LoginActivity
import com.example.marketplace.ui.toko.TokoSayaActivity
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.showToast
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private val viewModel: NavViewModel by viewModel() // panggil model navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNav()

        getUser()

    }

    private fun getUser(){
        val user = Prefs.getUser()?.id ?: 0

//          panggil function cek toko user
        viewModel.getUser(user).observe(this) {}
    }

    private fun setupNav(){
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)

//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        kondisi ketika fragmenya di klik /di pilih
        navView.setOnItemSelectedListener {


//            cek status login
            if(it.itemId == R.id.navigation_akun) {
                if(Prefs.isLogin) {
                    Log.d("TAG", "Sudah login")
                    navController.navigate(it.itemId)  // pindah fragmen
                }else {
//                    pindah activity
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "Belum login")
                    return@setOnItemSelectedListener false
                }

            }else{
                navController.navigate(it.itemId) // pindah fragmen
                Log.d("TAG", "onCreate: yang lain" +it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }
}