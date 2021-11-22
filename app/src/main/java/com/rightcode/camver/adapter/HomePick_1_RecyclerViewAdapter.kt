package com.rightcode.camver.adapter

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.camver.R
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.*

class HomePick_1_RecyclerViewAdapter(
    val context: Activity?,
    val data: ArrayList<type>
): RecyclerView.Adapter<HomePick_1_RecyclerViewAdapter.ViewHolder>()  {
    inner class ViewHolder(val binding: ItemHomePick1Binding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
            binding.ivThumnail.background = context?.getDrawable(R.drawable.no_color_background_corner_10)
            binding.ivThumnail.clipToOutline= true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           return ViewHolder(ItemHomePick1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }

}