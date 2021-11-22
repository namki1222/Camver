package com.rightcode.camver

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rightcode.ai_workplace_accident.network.model.request.result.Pay
import com.rightcode.camver.Activity.BaseActivity
import com.rightcode.camver.adapter.PayRecyclerViewAdapter
import com.rightcode.camver.databinding.ActivityPayBinding

class PayActivity : BaseActivity<ActivityPayBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_pay)

    }

    override fun initClickListener() {
    }

    override fun initActivity() {
        var store_name = intent.getStringExtra("store_name").toString()
        var title = intent.getStringExtra("title").toString()
        var money :Int = intent.getIntExtra("money",0)
        var data = ArrayList<Pay>()
        data.add(Pay(store_name,title,money))
        var adapter = PayRecyclerViewAdapter(mContext, data)
        val layoutManager = LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false)
        dataBinding.rvList.setLayoutManager(layoutManager)
        dataBinding.rvList.setAdapter(adapter)
        dataBinding.tvAllProductMoneyMoney.setText(money.toString())
        dataBinding.tvPayAllMoney.setText(money.toString())

    }
}