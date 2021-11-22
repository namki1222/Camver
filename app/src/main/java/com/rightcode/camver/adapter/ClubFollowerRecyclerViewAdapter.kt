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
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.*

class ClubFollowerRecyclerViewAdapter(
    val context: Activity?,
    val data: ArrayList<type>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    inner class ListViewHolder(val binding: ItemFollowerBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ListViewHolder(ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListViewHolder).onBind(data.get(position))
    }
    override fun getItemCount(): Int {
        return data.size
    }

}