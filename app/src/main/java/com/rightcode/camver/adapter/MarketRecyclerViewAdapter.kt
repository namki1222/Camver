package com.rightcode.camver.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.rightcode.camver.Activity.HomeHeaderActivity
import com.rightcode.camver.Dialog.SortDialog
import com.rightcode.camver.R
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.*

class MarketRecyclerViewAdapter(
    val context: Activity?,
    val data: ArrayList<type>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    val HEADER_ADVERTISEMENT =0
    val SORT =1
    val LIST =2



    inner class HeaderViewHolder(val binding: ItemListHomeViewpagerBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
        }
    }
    inner class SortViewHolder(val binding: ListItemSortBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
        }
    }
    inner class ListViewHolder(val binding: ListItemMarketBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
            binding.ivThumnail.background = context?.getDrawable(R.drawable.no_color_background_corner_10)
            binding.ivThumnail.clipToOutline= true
            binding.tvTitle.setText(item.name)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HEADER_ADVERTISEMENT) {
            return HeaderViewHolder(ItemListHomeViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else if(viewType == SORT){
            return SortViewHolder(ListItemSortBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else {
            return ListViewHolder(ListItemMarketBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data.get(position).type) {
            0 -> {
                (holder as HeaderViewHolder).onBind(data.get(position))
            }
            1 -> {
                (holder as SortViewHolder).onBind(data.get(position))
            }
            2 -> {
                (holder as ListViewHolder).onBind(data.get(position))
            }

        }
    }
    override fun getItemViewType(position: Int): Int {
        var viewtype : Int = 0
        if (position < data!!.size) {
            when (data[position].type) {
                0 ->{ viewtype = HEADER_ADVERTISEMENT }
                1 ->{ viewtype = SORT}
                2 ->{ viewtype = LIST}
            }
        }
        return viewtype
    }

    override fun getItemCount(): Int {
        return data.size
    }

}