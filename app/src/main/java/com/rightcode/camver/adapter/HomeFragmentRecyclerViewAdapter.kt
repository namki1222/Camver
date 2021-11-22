package com.rightcode.camver.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.camver.Activity.HomeHeaderActivity
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.ItemListHomeAdvertiseBinding
import com.rightcode.camver.databinding.ItemListHomePickBinding
import com.rightcode.camver.databinding.ItemListHomeRvHeader0Binding
import com.rightcode.camver.databinding.ItemListHomeViewpagerBinding

class HomeFragmentRecyclerViewAdapter(
    val context: Activity?,
    val data: ArrayList<type>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    val HEADER =0
    val ADVERTISEMENT =1
    val PICK =2
    val ONEADVERTISEMENT =3



    inner class HeaderViewHolder(val binding: ItemListHomeRvHeader0Binding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
            binding.ivList1.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","캠핑카")
                context?.startActivity(intent)
            }

            binding.ivList2.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","차박")
                context?.startActivity(intent)
            }

            binding.ivList3.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","캠핑장")
                context?.startActivity(intent)
            }

            binding.ivList4.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","호텔")
                context?.startActivity(intent)
            }

            binding.ivList5.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","펜션")
                context?.startActivity(intent)
            }

            binding.ivList6.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","리조트")
                context?.startActivity(intent)
            }

            binding.ivList7.setOnClickListener{
                val intent = Intent(context, HomeHeaderActivity::class.java)
                intent.putExtra("Title","패키지")
                context?.startActivity(intent)
            }
        }
    }
    inner class AdvertiseViewHolder(val binding: ItemListHomeViewpagerBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {

        }
    }
    inner class PickViewHolder(val binding: ItemListHomePickBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {
            when(data.get(position).type){
                2->{
                    var data = ArrayList<type>()
                    data?.add(type(0,"header"))
                    data?.add(type(1,"ad_viewpager"))
                    data?.add(type(2,"캠버품 pick 추천상품"))
                    data?.add(type(3,"편하게, 캠핑카"))
                    binding.tvHeaderName.setText(item.name)
                    val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    binding.rvList.setLayoutManager(layoutManager)
                    binding.rvList.adapter = HomePick_1_RecyclerViewAdapter(context,data)
                }
                3->{
                    var data = ArrayList<type>()
                    data?.add(type(0,"header"))
                    data?.add(type(1,"ad_viewpager"))
                    data?.add(type(2,"캠버품 pick 추천상품"))
                    data?.add(type(3,"편하게, 캠핑카"))
                    binding.tvHeaderName.setText(item.name)
                    val gridLayout = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
                    binding.rvList.setLayoutManager(gridLayout)
                    binding.rvList.addItemDecoration(SpaceDecoration(25))
                    binding.rvList.adapter = HomePick_2_RecyclerViewAdapter(context,data)
                }
                4,5,7,8,9,11->{
                    var data = ArrayList<type>()
                    data?.add(type(0,"header"))
                    data?.add(type(1,"ad_viewpager"))
                    data?.add(type(2,"캠버품 pick 추천상품"))
                    data?.add(type(3,"편하게, 캠핑카"))
                    binding.tvHeaderName.setText(item.name)
                    val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                    binding.rvList.setLayoutManager(layoutManager)
                    binding.rvList.adapter = HomePick_3_RecyclerViewAdapter(context,data)
                }
            }
        }
    }
    inner class OneAdverViewHolder(val binding: ItemListHomeAdvertiseBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: type) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HEADER) {
            return HeaderViewHolder(ItemListHomeRvHeader0Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else if(viewType == ADVERTISEMENT){
            return AdvertiseViewHolder(ItemListHomeViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else if(viewType == PICK){
            return PickViewHolder(ItemListHomePickBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{
            return OneAdverViewHolder(ItemListHomeAdvertiseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (data.get(position).type) {
            0 -> {
                (holder as HeaderViewHolder).onBind(data.get(position))
            }
            1 -> {
                (holder as AdvertiseViewHolder).onBind(data.get(position))
            }
            2,3,4,5,7,8,9,11 -> {
                (holder as PickViewHolder).onBind(data.get(position))
            }
            6,10 -> {
                (holder as OneAdverViewHolder).onBind(data.get(position))
            }

        }
    }
    override fun getItemViewType(position: Int): Int {
        var viewtype : Int = 0
        if (position < data!!.size) {
            when (data[position].type) {
                0 ->{ viewtype = HEADER }
                1 ->{ viewtype = ADVERTISEMENT}
                2,3,4,5,7,8,9,11 ->{ viewtype = PICK}
                6,10 ->{viewtype = ONEADVERTISEMENT}
            }
        }
        return viewtype
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
class SpaceDecoration(private val size: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect,view,parent,state)
        if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.left += size
        }else{
            outRect.right += size
        }
    }
}