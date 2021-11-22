package com.rightcode.camver.Activity

import android.os.Bundle
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.databinding.ActivityFindPwdOkBinding
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.network.model.response.PasswordChange

class FindPwdOkActivity : BaseActivity<ActivityFindPwdOkBinding>() {
    lateinit var  id: String
    lateinit var  phone_num : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_find_pwd_ok)
    }

    override fun initActivity() {
        var intent = getIntent()
        id = intent.getStringExtra("Id").toString()
        phone_num = intent.getStringExtra("phone").toString()
    }
    override fun initClickListener() {
        dataBinding.tvStart.setOnClickListener {
            change_pwd()
            finishWithAnim() }
    }
    fun change_pwd(){
        if(dataBinding.etPwd.text.toString().equals(dataBinding.etPwdCheck.text.toString())) {
            val data = PasswordChange(id,dataBinding.etPwd.text.toString(),phone_num)
            callApi(
                NetworkManager.getInstance(mContext)?.getApiService()?.pwd_change(data),
                object : ApiResponseHandler<CommonResponse> {
                    override fun onSuccess(result: CommonResponse?) {
                        Log.d("통신성공!!!!!!!!!!")
                    }

                    override fun onServerFail(result: CommonResponse?) {
                        Log.d("통신실패!!!!!!!!!!")

                    }

                    override fun onNoResponse(result: CommonResponse?) {
                        Log.d("응답없음!!!!!!!")
                    }

                    override fun onBadRequest(t: Throwable?) {
                        Log.d("!!!!!잘못됨!!")
                    }

                })
        }
    }
}