package com.example.marketplace.ui.alamatToko.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.databinding.ItemAlamatTokoBinding

class AlamatTokoAdapter : RecyclerView.Adapter<AlamatTokoAdapter.ViewHolder>(){

    private var data = ArrayList<AlamatToko>()

//    class di dalam class
    inner class ViewHolder(private val itemBinding: ItemAlamatTokoBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: AlamatToko, position: Int) {
            itemBinding.apply {

                var kecamatan = ""
                if(item.kecamatan !=null ) kecamatan = ", Kec. ${item.kecamatan}"

                tvKota.text = item.kota
                tvAlamat.text = "${item.alamat}$kecamatan , ${item.kota}, ${item.provinsi}, ${item.kodepos}"
                tvPhone.text = item.phone
                tvEmail.text = item.email
            }
        }
    }

    fun addItems(items: List<AlamatToko>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAlamatTokoBinding.inflate(
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