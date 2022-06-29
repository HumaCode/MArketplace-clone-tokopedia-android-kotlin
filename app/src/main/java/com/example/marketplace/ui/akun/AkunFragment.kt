package com.example.marketplace.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.ui.navigation.NavigationActivity
import com.example.marketplace.databinding.FragmentAkunBinding
import com.example.marketplace.ui.toko.BukaTokoActivity
import com.example.marketplace.ui.toko.TokoSayaActivity
import com.example.marketplace.ui.updateProfile.UpdateProfileActivity
import com.example.marketplace.util.Constants.USER_URL
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val akunViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root


        mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false // ubah status loginjadi false
            pushActivity(NavigationActivity::class.java)  // pindah activity
        }

        binding.btnUpdate.setOnClickListener {
            intentActivity(UpdateProfileActivity::class.java)
        }
    }

    private fun setUser() {
//        ambil data user / untuk di tampilkan
        val user = Prefs.getUser()
        if(user != null) {
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                inisial.text = user.name.getInitial()

//                menampilkan gambar/foto user
                Picasso.get().load(USER_URL + user.image).into(binding.imageProfile)


//                cek toko user
                if(user.toko != null) {
                    tvStatusToko.toGone()
                    tvNamaToko.text = user.toko?.name

                    binding.btnToko.setOnClickListener {
                        intentActivity(TokoSayaActivity::class.java)
                    }
                }else{
                    binding.btnToko.setOnClickListener {
                        intentActivity(BukaTokoActivity::class.java)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}