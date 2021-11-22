package com.rightcode.camver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.adapter.CampingCarDetailViewPagerAdapter
import com.rightcode.camver.databinding.FragmentDetail1Binding
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.network.model.response.ProductdetailResponse

/**
 * A simple [Fragment] subclass.
 * Use the [Detail1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Detail1Fragment : BaseFragment<FragmentDetail1Binding>() {
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
        return bindView(R.layout.fragment_detail_1, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {
        var param : Int? = getArguments()?.getInt("param")
        if (param != null) {
            product_detail(param)
        }
    }

    override fun initClickListener() {
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(): Detail1Fragment {
            val fragment = Detail1Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
    fun product_detail(id :Int) {
        callApi(
            activity?.let { NetworkManager.getInstance(it)?.getApiService()?.product_detail(id) },
            object : ApiResponseHandler<ProductdetailResponse> {
                override fun onSuccess(result: ProductdetailResponse?) {
                    Log.d("통신성공!!!!!!!!!!")
                    if(result?.data?.images!=null){
                        Log.d(result?.data?.images+"!!!!")
                        var adapter : CampingCarDetailViewPagerAdapter
                        adapter = CampingCarDetailViewPagerAdapter(activity,result.data.images);
                        dataBinding.rvList.setAdapter(adapter);
                        dataBinding.rvList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                                var page :String = (position + 1).toString()+"/"+dataBinding.rvList.adapter?.itemCount
                                dataBinding.txtViewpagercount.text = page
                            }
                        })
                    }
                }

                override fun onServerFail(result: CommonResponse?) {
                    Log.d("통신실패!!!!!!!!!!")

                }

                override fun onNoResponse(result: ProductdetailResponse?) {
                    Log.d("응답없음!!!!!!!")
                }

                override fun onBadRequest(t: Throwable?) {
                    Log.d("!!!!!잘못됨!!")
                }

            })
    }
}