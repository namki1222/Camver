package com.rightcode.camver.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.ai_workplace_accident.network.model.request.result.Product_list
import com.rightcode.camver.Activity.CampingCarDetailActivity
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.*
import java.text.NumberFormat

class CampingCarRecyclerViewAdapter(
    val context: Context,
    val data: ArrayList<Product_list>
): RecyclerView.Adapter<CampingCarRecyclerViewAdapter.ViewHolder>()  {
    inner class ViewHolder(val binding: ItemCampingListBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Product_list) {
            binding.tvTitle.setText(item.name)
            binding.tvMemo.setText(item.memo)
            binding.tvAdress.setText(item.address)
            binding.tvAverage.setText(item.averageRate.toString())
            binding.tvTotalReview.setText("("+item.reviewCount.toString()+")")
            binding.tvPrice.setText(NumberFormat.getNumberInstance().format(Integer.valueOf(item.priceBasic)).toString()+"원")
            binding.ivStar1.isSelected = true
            itemView.setOnClickListener({
                val intent = Intent(context, CampingCarDetailActivity::class.java)
                intent.putExtra("id",item.id)
                intent.putExtra("avarage",item.averageRate)
                intent.putExtra("review_count",item.reviewCount)
                context.startActivity(intent)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           return ViewHolder(ItemCampingListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }

}