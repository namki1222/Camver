package com.rightcode.camver.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.camver.Activity.HomeSearchActivity
import com.rightcode.camver.R
import com.rightcode.camver.adapter.HomeFragmentRecyclerViewAdapter
import com.rightcode.camver.adapter.model.type
import com.rightcode.camver.databinding.FragmentMenu1Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Menu1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu1Fragment : BaseFragment<FragmentMenu1Binding>() {
    private var param1: String? = null
    private var param2: String? = null
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
        return bindView(R.layout.fragment_menu_1, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        dataBinding.rvHome.setLayoutManager(layoutManager)
        var data = ArrayList<type>()
        data?.add(type(0,"header"))
        data?.add(type(1,"ad_viewpager"))
        data?.add(type(2,"캠버품 pick 추천상품"))
        data?.add(type(3,"편하게, 캠핑카"))
        data?.add(type(4,"어디든 갈 수 있어, 차박"))
        data?.add(type(5,"캠핑의 오리지널 캠핑장"))
        data?.add(type(6,"one_page_ad"))
        data?.add(type(7,"호캉스 즐기자! 호텔"))
        data?.add(type(8,"자연 속에서 힐링, 펜션"))
        data?.add(type(9,"호화를 누리는, 리조트"))
        data?.add(type(10,"one_page_ad"))
        data?.add(type(11,"간편하게 즐기자! 패키지"))
        dataBinding.rvHome.adapter = HomeFragmentRecyclerViewAdapter(activity,data)
    }

    override fun initClickListener() {
        dataBinding.ivSearch.setOnClickListener({
            val intent = Intent(activity, HomeSearchActivity::class.java)
            startActivity(intent)
        })
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(): Menu1Fragment {
            val fragment = Menu1Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}