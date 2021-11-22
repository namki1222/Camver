package com.rightcode.camver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.rightcode.ai_workplace_accident.network.model.request.result.option_data
import com.rightcode.ai_workplace_accident.network.model.request.result.options
import com.rightcode.camver.Dialog.DatePickerDialog
import com.rightcode.camver.Dialog.optionDialog
import com.rightcode.camver.PayActivity
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.adapter.CampingCarDetailViewPagerAdapter
import com.rightcode.camver.databinding.ActivityCampingCarDetailBinding
import com.rightcode.camver.fragment.*
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.network.model.response.ProductdetailResponse

class CampingCarDetailActivity : BaseActivity<ActivityCampingCarDetailBinding>() {
    var id : Int =0
    lateinit var name : String
    var fm: FragmentManager? = null
    var transaction: FragmentTransaction? = null
     lateinit var data :ArrayList<option_data>
    lateinit var options : ArrayList<options>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_camping_car_detail)
    }

    override fun initClickListener() {
        dataBinding.tvStart.setOnClickListener{
            var dialog = optionDialog(mContext)
            dialog.show(supportFragmentManager, null)
            dialog.setoptions(options)
            dialog.setListener{
                this.data = it
                val intent = Intent(mContext, PayActivity::class.java)
                intent.putExtra("store_name",name)
//                intent.putExtra("day",item.id)
                intent.putExtra("title",it.get(0).title)
                intent.putExtra("money",(it.get(0).count * it.get(0).price))
                mContext.startActivity(intent)
            }
        }
        dataBinding.tvDetail.setOnClickListener{
            set_menu(0)
            replaceFragment(Detail1Fragment.newInstance(),id)
        }
        dataBinding.tvReview.setOnClickListener{
            set_menu(1)
            replaceFragment(Detail2Fragment.newInstance())

        }
        dataBinding.tvQnA.setOnClickListener{
            set_menu(2)

        }
        dataBinding.tvCancel.setOnClickListener{
            replaceFragment(Detail4Fragment.newInstance())
            set_menu(3)

        }
    }

    override fun initActivity() {
        fm = supportFragmentManager
        id = getIntent().getIntExtra("id",0)
        var avarage = getIntent().getFloatExtra("avarage",0f)
        var review_total = getIntent().getIntExtra("review_count",0)
        replaceFragment(Detail1Fragment.newInstance(),id)
        product_detail(id,avarage,review_total)
        set_menu(0)
    }
    private fun replaceFragment(fragment: Fragment,id:Int) {
        var bundle = Bundle()
        bundle.putInt("param",id)
        fragment.setArguments(bundle);
        transaction = fm!!.beginTransaction()
        transaction!!.replace(R.id.frame_layout, fragment)
        transaction!!.commit()
    }
    private fun replaceFragment(fragment: Fragment) {
        transaction = fm!!.beginTransaction()
        transaction!!.replace(R.id.frame_layout, fragment)
        transaction!!.commit()
    }
    fun set_menu(num:Int){
        if(num == 0){
            dataBinding.tvDetail.isSelected = true
            dataBinding.tvReview.isSelected = false
            dataBinding.tvQnA.isSelected = false
            dataBinding.tvCancel.isSelected = false

            dataBinding.txtTabLine1.isSelected = true
            dataBinding.txtTabLine2.isSelected = false
            dataBinding.txtTabLine3.isSelected = false
            dataBinding.txtTabLine4.isSelected = false
        }else if(num == 1){
            dataBinding.tvDetail.isSelected = false
            dataBinding.tvReview.isSelected = true
            dataBinding.tvQnA.isSelected = false
            dataBinding.tvCancel.isSelected = false

            dataBinding.txtTabLine1.isSelected = false
            dataBinding.txtTabLine2.isSelected = true
            dataBinding.txtTabLine3.isSelected = false
            dataBinding.txtTabLine4.isSelected = false
        }else if(num == 2){
            dataBinding.tvDetail.isSelected = false
            dataBinding.tvReview.isSelected = false
            dataBinding.tvQnA.isSelected = true
            dataBinding.tvCancel.isSelected = false

            dataBinding.txtTabLine1.isSelected = false
            dataBinding.txtTabLine2.isSelected = false
            dataBinding.txtTabLine3.isSelected = true
            dataBinding.txtTabLine4.isSelected = false
        }else if(num == 3){
            dataBinding.tvDetail.isSelected = false
            dataBinding.tvReview.isSelected = false
            dataBinding.tvQnA.isSelected = false
            dataBinding.tvCancel.isSelected = true

            dataBinding.txtTabLine1.isSelected = false
            dataBinding.txtTabLine2.isSelected = false
            dataBinding.txtTabLine3.isSelected = false
            dataBinding.txtTabLine4.isSelected = true
        }
    }
    fun product_detail(id :Int,avarage :Float,review_total :Int) {
        callApi(
            NetworkManager.getInstance(mContext)?.getApiService()?.product_detail(id),
            object : ApiResponseHandler<ProductdetailResponse> {
                override fun onSuccess(result: ProductdetailResponse?) {
                    Log.d("통신성공!!!!!!!!!!")
                    if(result?.data?.images!=null){
                        name = result.data.name
                        Log.d(result?.data?.images+"!!!!")
                        var adapter : CampingCarDetailViewPagerAdapter
                        options = result.data.options
                        adapter = CampingCarDetailViewPagerAdapter(mContext,result.data.images);
                        dataBinding.rvList.setAdapter(adapter);
                        dataBinding.rvList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                            override fun onPageSelected(position: Int) {
                                super.onPageSelected(position)
                                var page :String = (position + 1).toString()+"/"+dataBinding.rvList.adapter?.itemCount
                                dataBinding.txtViewpagercount.text = page
                            }
                        })
                        dataBinding.tvAdress.setText(result.data.address)
                        dataBinding.tvName.setText(result.data.name)
                        dataBinding.tvMemo.setText(result.data.memo)
                        dataBinding.tvAdressDetail.setText(result.data.addressDetail)
                        dataBinding.tvReviewCount.setText("("+review_total.toString()+")")
                        dataBinding.tvAverage.setText(avarage.toString())
                        review_start(avarage)
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
    fun review_start(avarage :Float){
        if(avarage<=5&&avarage>=4.5){
            dataBinding.ivStar1.isSelected = true
            dataBinding.ivStar2.isSelected = true
            dataBinding.ivStar3.isSelected = true
            dataBinding.ivStar4.isSelected = true
            dataBinding.ivStar5.isSelected = true
        }else if(avarage>=3.5&&avarage<4.5){
            dataBinding.ivStar1.isSelected = true
            dataBinding.ivStar2.isSelected = true
            dataBinding.ivStar3.isSelected = true
            dataBinding.ivStar4.isSelected = true
            dataBinding.ivStar5.isSelected = false
        }else if(avarage>=2.5&&avarage<3.5){
            dataBinding.ivStar1.isSelected = true
            dataBinding.ivStar2.isSelected = true
            dataBinding.ivStar3.isSelected = true
            dataBinding.ivStar4.isSelected = false
            dataBinding.ivStar5.isSelected = false
        }else if(avarage>=1.5&&avarage<2.5){
            dataBinding.ivStar1.isSelected = true
            dataBinding.ivStar2.isSelected = true
            dataBinding.ivStar3.isSelected = false
            dataBinding.ivStar4.isSelected = false
            dataBinding.ivStar5.isSelected = false
        }else if(avarage>0.0&&avarage<1.5){
            dataBinding.ivStar1.isSelected = true
            dataBinding.ivStar2.isSelected = false
            dataBinding.ivStar3.isSelected = false
            dataBinding.ivStar4.isSelected = false
            dataBinding.ivStar5.isSelected = false
        }else if(avarage==0f){
            dataBinding.ivStar1.isSelected = false
            dataBinding.ivStar2.isSelected = false
            dataBinding.ivStar3.isSelected = false
            dataBinding.ivStar4.isSelected = false
            dataBinding.ivStar5.isSelected = false
        }
    }
}