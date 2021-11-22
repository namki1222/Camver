package com.rightcode.camver.Activity

import android.content.Intent
import android.os.Bundle
import com.rightcode.ai_workplace_accident.network.model.request.result.login
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.Util.PreferenceUtil
import com.rightcode.camver.Util.ToastUtil
import com.rightcode.camver.databinding.ActivityLoginBinding
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.network.model.response.LoginResponse

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_login)
    }

    override fun initActivity() {}
    override fun initClickListener() {
        dataBinding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        dataBinding.tvFindid.setOnClickListener {
            val intent = Intent(this, FindIdActivity::class.java)
            startActivity(intent)
        }
        dataBinding.tvPasschange.setOnClickListener {
            val intent = Intent(this, FindPwdActivity::class.java)
            startActivity(intent)
        }
        dataBinding.tvStart.setOnClickListener {
            login_activity()
        }
    }
    fun login_activity(){
        val data  = login(dataBinding.etId.text.toString(),dataBinding.etPwd.text.toString())
        callApi(NetworkManager.getInstance(mContext)?.getApiService()?.login(data), object :
            ApiResponseHandler<LoginResponse> {
            override fun onSuccess(result: LoginResponse?) {
                Log.d("통신성공!!!!!!!!!!")
                val pref: PreferenceUtil = PreferenceUtil.getInstance(mContext)
                pref.put(PreferenceUtil.PreferenceKey.Token, result?.token)
                val intent = Intent(mContext, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onServerFail(result: CommonResponse?) {
                Log.d("통신실패!!!!!!!!!!")
                ToastUtil.show(mContext,result?.message)
            }

            override fun onNoResponse(result: LoginResponse?) {
                Log.d("응답없음!!!!!!!")
            }

            override fun onBadRequest(t: Throwable?) {
                Log.d("!!!!!잘못됨!!")
            }

        })

    }
}