package com.example.marketplace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marketplace.databinding.ActivityNavigationBinding
import com.example.marketplace.ui.login.LoginActivity
import com.example.marketplace.util.Prefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_keranjang
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        kondisi ketika fragmenya di klik /di pilih
        navView.setOnItemSelectedListener {


//            cek status login
            if(it.itemId == R.id.navigation_notifications) {
                val s = Prefs(this)
                if(s.getIsLogin()) {
                    Log.d("TAG", "Sudah login")
                    navController.navigate(it.itemId)  // pindah fragmen
                }else {
//                    pindah activity
                    startActivity(Intent(this, LoginActivity::class.java))
                    Log.d("TAG", "Belum login")
                }

            }else{
                navController.navigate(it.itemId) // pindah fragmen
                Log.d("TAG", "onCreate: yang lain" +it.itemId)
            }

            return@setOnItemSelectedListener true
        }
    }
}