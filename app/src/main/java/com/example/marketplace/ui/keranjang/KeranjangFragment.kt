package com.example.marketplace.ui.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.databinding.FragmentKeranjangBinding

class KeranjangFragment : Fragment() {

    private var _binding: FragmentKeranjangBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val keranjangViewModel =
            ViewModelProvider(this).get(KeranjangViewModel::class.java)

        _binding = FragmentKeranjangBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}