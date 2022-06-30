package com.example.marketplace.ui.alamatToko.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.AlamatToko
import com.example.marketplace.databinding.ItemAlamatTokoBinding
import com.example.marketplace.ui.alamatToko.EditAlamatTokoActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson

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

//                menu edit
                val context = root.context
                btnMenu.setOnClickListener {
                    val listMenu = listOf("Edit", "Hapus")
                    root.context.popUpMenu(btnMenu, listMenu) {
                        when (it){
                            "Edit" -> context.intentActivity(EditAlamatTokoActivity::class.java, item.toJson())
                            "Hapus" -> logs("Hapus")
                        }
                    }
                }
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