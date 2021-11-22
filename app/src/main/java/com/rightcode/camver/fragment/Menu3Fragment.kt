package com.rightcode.camver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.rightcode.camver.R
import com.rightcode.camver.adapter.ClubFollowerRecyclerViewAdapter
import com.rightcode.camver.adapter.ClubRecyclerViewAdapter
import com.rightcode.camver.adapter.HomeFragmentRecyclerViewAdapter
import com.rightcode.camver.adapter.SpaceDecoration
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.FragmentMenu3Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Menu3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu3Fragment : BaseFragment<FragmentMenu3Binding>() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(Menu3Fragment.ARG_PARAM1)
            param2 = it.getString(Menu3Fragment.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return bindView(R.layout.fragment_menu_3, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {
        val layoutManager_follower = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val layoutManager_feed = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        dataBinding.rvFeed.setLayoutManager(layoutManager_feed)
        dataBinding.rvFeed.addItemDecoration(SpaceDecoration(25))
        dataBinding.rvFollower.setLayoutManager(layoutManager_follower)
        var data = ArrayList<type>()
        data?.add(type(1,"header"))
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
        dataBinding.rvFollower.adapter = ClubFollowerRecyclerViewAdapter(activity,data)
        dataBinding.rvFeed.adapter = ClubRecyclerViewAdapter(activity,data)
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
        fun newInstance(): Menu3Fragment {
            val fragment = Menu3Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}