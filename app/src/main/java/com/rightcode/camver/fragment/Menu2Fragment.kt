package com.rightcode.camver.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.rightcode.camver.Activity.HomeSearchActivity
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.adapter.CampingRecyclerViewAdapter
import com.rightcode.camver.adapter.ClubFollowerRecyclerViewAdapter
import com.rightcode.camver.adapter.ClubRecyclerViewAdapter
import com.rightcode.camver.adapter.SpaceDecoration
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.FragmentMenu2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Menu2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu2Fragment : BaseFragment<FragmentMenu2Binding>() {
    private var param1: String? = null
    private var param2: String? = null

    var mTopFragment:TopFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return bindView(R.layout.fragment_menu_2, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {

        val intent = Intent(activity, HomeSearchActivity::class.java)
        mTopFragment = childFragmentManager.findFragmentByTag("TopFragment") as TopFragment
        mTopFragment?.setDefaultFormat("캠핑장",View.OnClickListener { startActivity(intent) },"RIGHT,SEARCH")
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        dataBinding.rvList.setLayoutManager(layoutManager)
        var data = ArrayList<type>()
        data?.add(type(0,"header"))
        data?.add(type(1,"ad_viewpager"))
        data?.add(type(1,"캠버품 pick 추천상품"))
        data?.add(type(1,"편하게, 캠핑카"))
        data?.add(type(1,"어디든 갈 수 있어, 차박"))
        data?.add(type(1,"캠핑의 오리지널 캠핑장"))
        data?.add(type(1,"one_page_ad"))
        data?.add(type(1,"호캉스 즐기자! 호텔"))
        data?.add(type(1,"자연 속에서 힐링, 펜션"))
        data?.add(type(1,"호화를 누리는, 리조트"))
        data?.add(type(1,"one_page_ad"))
        data?.add(type(1,"간편하게 즐기자! 패키지"))
        dataBinding.rvList.adapter = CampingRecyclerViewAdapter(activity,data)
        dataBinding.tvAverage.setOnClickListener{
            if(dataBinding.tvAverage.isSelected ==false){
                dataBinding.tvAverage.isSelected =true
            }else{
                dataBinding.tvAverage.isSelected =false
            }
        }
        dataBinding.tvNear.setOnClickListener{
            if(dataBinding.tvNear.isSelected ==false){
                dataBinding.tvNear.isSelected =true
            }else{
                dataBinding.tvNear.isSelected =false
            }
        }
        dataBinding.tvUpdate.setOnClickListener{
            if(dataBinding.tvUpdate.isSelected ==false){
                dataBinding.tvUpdate.isSelected =true
            }else{
                dataBinding.tvUpdate.isSelected =false
            }
        }
    }
    override fun initClickListener() {
        dataBinding.tabLayoout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(): Menu2Fragment {
            val fragment = Menu2Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}