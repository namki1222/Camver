package com.rightcode.camver.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.ai_workplace_accident.network.model.request.result.Product_list
import com.rightcode.ai_workplace_accident.network.model.request.result.categories
import com.rightcode.ai_workplace_accident.network.model.request.result.option_data
import com.rightcode.camver.Activity.CampingCarDetailActivity
import com.rightcode.camver.Util.Log
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.*
import lombok.Getter
import lombok.Setter
import java.text.NumberFormat

class OptionRecyclerViewAdapter(
    val context: Context,
    var data: ArrayList<option_data>
): RecyclerView.Adapter<OptionRecyclerViewAdapter.ViewHolder>()  {


    lateinit var listener: RemoveOnclick
    lateinit var listener_add: AddOnclick
    lateinit var listener_exit: ExitOnclick

    inner class ViewHolder(val binding: ListItemOptionBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: option_data) {
            Log.d(item.count)
            binding.tvTitle.setText(item.title)
            binding.tvCount.setText(item.count.toString())
            binding.tvTotal.setText(item.price.toString())
            binding.ivMinus.setOnClickListener{
                listener.onClick(adapterPosition)
            }
            binding.ivAdd.setOnClickListener{
                listener_add.onClick(adapterPosition)
            }
            binding.ivExitOption.setOnClickListener{
                listener_exit.onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           return ViewHolder(ListItemOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }

    interface RemoveOnclick{
        fun onClick(position:Int)
    }
    interface AddOnclick{
        fun onClick(position:Int)
    }
    interface ExitOnclick{
        fun onClick(position:Int)
    }
}