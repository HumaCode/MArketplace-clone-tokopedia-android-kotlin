package com.example.marketplace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marketplace.databinding.FragmentHomeBinding
import com.example.marketplace.ui.home.adapter.CategoryAdapter
import com.example.marketplace.ui.home.adapter.ProductTerbaruAdapter
import com.example.marketplace.ui.home.adapter.ProductTerlarisAdapter
import com.example.marketplace.ui.home.adapter.SliderAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()
    private val adapterProdukTerlaris = ProductTerlarisAdapter()
    private val adapterProdukTerbaru = ProductTerbaruAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAdapter()
        setData()
//        getData()
        return root
    }

//    memanggil adapter
    private fun setupAdapter() {
        binding.rvCategory.adapter = adapterCategory
        binding.rvSlider.adapter = adapterSlider
        binding.rvProdukTerlaris.adapter = adapterProdukTerlaris
        binding.rvProdukTerbaru.adapter = adapterProdukTerbaru
    }

    private fun setData() {
//        category
        viewModel.listCategory.observe(requireActivity()) {
            adapterCategory.addItems(it)
        }

//        slider
        viewModel.listSlider.observe(requireActivity()) {
            adapterSlider.addItems(it)
        }

//        produk
        viewModel.listProduk.observe(requireActivity()) {
            adapterProdukTerlaris.addItems(it)
            adapterProdukTerbaru.addItems(it)
        }
    }


    private fun getData(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}