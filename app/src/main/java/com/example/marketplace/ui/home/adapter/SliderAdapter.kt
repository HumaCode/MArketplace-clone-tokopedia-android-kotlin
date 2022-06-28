package com.example.marketplace.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.core.data.source.model.Category
import com.example.marketplace.core.data.source.model.Slider
import com.example.marketplace.databinding.ItemHomeCategoryBinding
import com.example.marketplace.databinding.ItemHomeSliderBinding

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.ViewHolder>(){

    private var data = ArrayList<Slider>()

//    class di dalam class
    inner class ViewHolder(private val itemBinding: ItemHomeSliderBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Slider, position: Int) {
            itemBinding.apply {
                imageView.setImageResource(item.image)
            }
        }
    }

    fun addItems(items: List<Slider>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeSliderBinding.inflate(
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