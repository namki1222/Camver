package com.rightcode.camver.Activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.rightcode.camver.R
import com.rightcode.camver.adapter.CampingCarRecyclerViewAdapter
import com.rightcode.camver.databinding.ActivityCampingCarBinding
import com.rightcode.camver.fragment.TopFragment
import com.rightcode.camver.Dialog.DatePickerDialog
import com.rightcode.camver.Dialog.PeopleDialog
import com.rightcode.camver.Dialog.SortDialog
import com.rightcode.camver.Util.Log
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.network.model.response.ProductListResponse

class HomeHeaderActivity : BaseActivity<ActivityCampingCarBinding>() {
    lateinit var date: String
    lateinit var sort_dialog: String
    var sort: String = "전체"
    lateinit var title: String

    var mTopFragment: TopFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_camping_car)
    }

    override fun initClickListener() {
        dataBinding.tvDatePick.setOnClickListener {
            var dialog = DatePickerDialog(mContext)
            dialog.show(supportFragmentManager, null)
            dialog.setListener {
                date = it
            }
        }
        dataBinding.tvPeople.setOnClickListener {
            var dialog = PeopleDialog(mContext)
            dialog.show(supportFragmentManager, null)
        }
        dataBinding.tvPopulor.setOnClickListener {
            var dialog = SortDialog(mContext)
            dialog.show(supportFragmentManager, null)
            dialog.setListener {
                sort_dialog = it
            }
        }
    }

    override fun initActivity() {
        title = getIntent().getStringExtra("Title").toString()
        mTopFragment = supportFragmentManager.findFragmentByTag("TopFragment") as TopFragment
        mTopFragment?.setDefaultFormat(title, { finishWithAnim() }, "LEFT")
        when (title) {
            "캠핑카" -> {
                set_area("전체", "스타렉스", "쏠라티", "레이", "모터홈", "1박2일", "2박3일", "장박", "", "", 8)
            }
            "차박" -> {
                set_area("전체", "스타렉스", "쏠라티", "레이", "1박2일", "2박3일", "장박", "", "", "", 7)
            }
            "캠핑장" -> {
                set_area("전체", "수도권", "경기권", "강원권", "경상권", "전라권", "제주권", "충청권", "", "", 8)
            }
            "호텔" -> {
                set_area("전체", "서울", "부산", "제주", "강원", "경기", "인천", "경상", "전라", "충청", 10)
            }
            "펜션" -> {
                set_area("전체", "경기권", "강원권", "경상권", "충청권", "전라권", "제주권", "", "", "", 7)
            }
            "리조트" -> {
                set_area("전체", "경기권", "강원권", "경상권", "충청권", "전라권", "제주권", "", "", "", 7)
            }
            "패키지" -> {
                set_area("전체", "캠핑카+캠핑", "캠핑카+숙", "캠핑카+맛", "지캠핑카+놀거리", "", "", "", "", "", 5)
            }
        }
        product_list(title)
    }

    fun set_area(
        area_all: String,
        area_1: String,
        area_2: String,
        area_3: String,
        area_4: String,
        area_5: String,
        area_6: String,
        area_7: String,
        area_8: String,
        area_9: String,
        size: Int
    ) {
        dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_all))
        dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_1))
        dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_2))
        dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_3))
        if (size > 4) {
            dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_4))
            if (size > 5) {
                dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_5))
                if (size > 6) {
                    dataBinding.tabLayoout.addTab(dataBinding.tabLayoout.newTab().setText(area_6))
                    if (size > 7) {
                        dataBinding.tabLayoout.addTab(
                            dataBinding.tabLayoout.newTab().setText(area_7)
                        )
                        if (size > 8) {
                            dataBinding.tabLayoout.addTab(
                                dataBinding.tabLayoout.newTab().setText(area_8)
                            )
                            if (size > 9) {
                                dataBinding.tabLayoout.addTab(
                                    dataBinding.tabLayoout.newTab().setText(area_9)
                                )
                            }
                        }
                    }
                }
            }
        }
        dataBinding.tabLayoout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        sort = area_all
                        Log.d(sort)
                        product_list(title)
                    }
                    1 -> {
                        sort = area_1
                        Log.d(sort)
                        product_list(title)
                    }
                    2 -> {
                        sort = area_2
                        Log.d(sort)
                        product_list(title)
                    }
                    3 -> {
                        sort = area_3
                        Log.d(sort)
                        product_list(title)
                    }
                    4 -> {
                        sort = area_4
                        Log.d(sort)
                        product_list(title)
                    }
                    5 -> {
                        sort = area_5
                        Log.d(sort)
                        product_list(title)
                    }
                    6 -> {
                        sort = area_6
                        Log.d(sort)
                        product_list(title)
                    }
                    7 -> {
                        sort = area_7
                        Log.d(sort)
                        product_list(title)
                    }
                    8 -> {
                        sort = area_8
                        Log.d(sort)
                        product_list(title)
                    }
                    9 -> {
                        sort = area_9
                        Log.d(sort)
                        product_list(title)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    fun product_list(diff: String) {
        callApi(NetworkManager.getInstance(mContext)?.getApiService()?.product_list(sort, diff),
            object : ApiResponseHandler<ProductListResponse> {
                override fun onSuccess(result: ProductListResponse?) {
                    Log.d("통신성공!!!!!!!!!!")
                    if (result != null) {
                        val layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
                        dataBinding.rvList.setLayoutManager(layoutManager)
                        dataBinding.rvList.adapter = CampingCarRecyclerViewAdapter(mContext, result.list)
                    }
                }

                override fun onServerFail(result: CommonResponse?) {
                    Log.d("통신실패!!!!!!!!!!")

                }

                override fun onNoResponse(result: ProductListResponse?) {
                    Log.d("응답없음!!!!!!!")
                }

                override fun onBadRequest(t: Throwable?) {
                    Log.d("!!!!!잘못됨!!")
                }

            })
    }
}