package com.example.marketplace.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.NavigationActivity
import com.example.marketplace.databinding.FragmentAkunBinding
import com.example.marketplace.ui.updateProfile.UpdateProfileActivity
import com.example.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity

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

        setUser()
        mainButton()
        return root
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
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}