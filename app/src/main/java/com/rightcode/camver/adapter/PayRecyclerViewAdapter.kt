package com.rightcode.camver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.ai_workplace_accident.network.model.request.result.Pay
import com.rightcode.ai_workplace_accident.network.model.request.result.option_data
import com.rightcode.camver.Util.Log
import com.rightcode.camver.databinding.ListItemPayBinding

class PayRecyclerViewAdapter(
    val context: Context,
    var data: ArrayList<Pay>
): RecyclerView.Adapter<PayRecyclerViewAdapter.ViewHolder>()  {
    inner class ViewHolder(val binding: ListItemPayBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Pay) {
            binding.tvMoney.setText(item.money.toString())
            binding.tvTitle.setText(item.title)
            binding.tvName.setText(item.name)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           return ViewHolder(ListItemPayBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }
}