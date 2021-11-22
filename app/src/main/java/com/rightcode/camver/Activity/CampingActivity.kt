package com.rightcode.camver.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.rightcode.camver.R
import com.rightcode.camver.adapter.HomePick_2_RecyclerViewAdapter
import com.rightcode.camver.adapter.SpaceDecoration
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.ActivityCampingCarDetailBinding
import com.rightcode.camver.databinding.ActivityCampingDetailBinding

class CampingActivity : BaseActivity<ActivityCampingDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_camping_detail)
    }

    override fun initClickListener() {
    }

    override fun initActivity() {
        var data = ArrayList<type>()
        data?.add(type(0,"header"))
        data?.add(type(1,"ad_viewpager"))
        data?.add(type(2,"캠버품 pick 추천상품"))
        data?.add(type(3,"편하게, 캠핑카"))
        val gridLayout = GridLayoutManager(mContext,2, GridLayoutManager.VERTICAL,false)
        dataBinding.rvList1.setLayoutManager(gridLayout)
        dataBinding.rvList1.addItemDecoration(SpaceDecoration(25))
        dataBinding.rvList1.adapter = HomePick_2_RecyclerViewAdapter(this,data)
    }
}