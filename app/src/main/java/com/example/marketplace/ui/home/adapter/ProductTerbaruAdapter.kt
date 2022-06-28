package com.example.marketplace.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.Product
import com.example.marketplace.core.data.source.model.Slider
import com.example.marketplace.databinding.ItemHomeProdukTerbaruBinding
import com.example.marketplace.databinding.ItemHomeProdukTerlarisBinding
import com.example.marketplace.databinding.ItemHomeSliderBinding
import com.inyongtisto.myhelper.extension.coret
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toRupiah
import com.inyongtisto.myhelper.extension.toVisible

class ProductTerbaruAdapter : RecyclerView.Adapter<ProductTerbaruAdapter.ViewHolder>(){

    private var data = ArrayList<Product>()

//    class di dalam class
    inner class ViewHolder(private val itemBinding: ItemHomeProdukTerbaruBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Product, position: Int) {
            itemBinding.apply {
                val harga = item.harga?:0
                val diskon = item.discount.toDouble()

                tvName.text = item.name
                imageView.setImageResource(item.image)
                tvHarga.text = item.harga.toRupiah()
                tvPengiriman.text = item.pengiriman
                tvRating.text = "" + item.rating + "| Terjual" + item.terjual + "Kali"

//                jika ada diskon
                if(item.discount != 0) {
                    lyGrosir.toGone()
                    lyDiskon.toVisible()
                    tvDiskon.text = "${item.discount}%"

                    tvHarga.text = (harga - ((diskon / 100)  * harga )).toRupiah()
                    tvHargaAsli.text = item.harga.toRupiah()
                    tvHargaAsli.coret()
                }
            }
        }
    }

    fun addItems(items: List<Product>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeProdukTerbaruBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}