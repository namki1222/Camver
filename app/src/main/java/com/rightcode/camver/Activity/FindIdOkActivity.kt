package com.rightcode.camver.Activity

import android.os.Bundle
import com.rightcode.camver.R
import com.rightcode.camver.databinding.ActivityFindIdOkBinding

class FindIdOkActivity : BaseActivity<ActivityFindIdOkBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_find_id_ok)
    }

    override fun initActivity() {
        var intent = getIntent()
        var id = intent.getStringExtra("id").toString()
        dataBinding.tvIdShow.text = id
    }
    override fun initClickListener() {
        dataBinding.tvStart.setOnClickListener { finishWithAnim() }
    }
}