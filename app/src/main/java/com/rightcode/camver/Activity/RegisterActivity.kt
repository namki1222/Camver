package com.rightcode.camver.Activity

import android.content.Intent
import android.os.Bundle
import com.rightcode.ai_workplace_accident.network.model.request.result.join
import com.rightcode.camver.Dialog.OneminuteDialog
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.Util.ToastUtil
import com.rightcode.camver.databinding.ActivityRegisterBinding
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    var start: Long = 0
    var button_select_sms: Boolean = true
    var id_same :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_register)
    }

    override fun initActivity() {
    }
    override fun initClickListener() {
        dataBinding.tvTest.setOnClickListener {
            id_check()
        }
        dataBinding.tvPhoneNumberConfirm.setOnClickListener{
            if(button_select_sms==true){
                if(dataBinding.etPhoneNum1.text.isEmpty()&&dataBinding.etPhoneNum2.text.isEmpty()){
                    ToastUtil.show(mContext,"전화번호를 입력하여주세요")
                }else{
                    start = System.currentTimeMillis()
                    phone_sms_send()
                }
            }else{
                var end : Long = System.currentTimeMillis()
                if((end-start)<60000){
                    val intent = Intent(this, OneminuteDialog::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }else if((end-start)>60000){
                    button_select_sms =true
                }
            }

        }
        dataBinding.tvPhoneConfirm.setOnClickListener{
            phone_sms_check()
        }
        dataBinding.tvStart.setOnClickListener {
            register()
        }
    }
    fun id_check(){
                callApi(NetworkManager.getInstance(mContext)?.getApiService()?.Idcheck(dataBinding.etConfirmId.text.toString()),
                    object : ApiResponseHandler<CommonResponse> {
                    override fun onSuccess(result: CommonResponse?) {
                        if (result?.message.toString().equals("아이디가 없습니다.")) {
                            ToastUtil.show(mContext, "사용 가능한 아이디입니다.")
                            id_same = true;
                        } else if (result?.message.toString().equals("아이디가 존재 합니다.")) {
                            ToastUtil.show(mContext, "사용중인 아이디입니다.")
                            id_same =false;
                        }
                    }

                    override fun onServerFail(result: CommonResponse?) {
                        if(result?.message.toString().equals("loginId should not be empty"))
                            ToastUtil.show(mContext,"아이디를 입력하여주세요.")
                        else
                            ToastUtil.show(mContext,result?.message)

                    }

                    override fun onNoResponse(result: CommonResponse?) {
                        Log.d("응답없음!!!!!!!")
                    }

                    override fun onBadRequest(t: Throwable?) {
                        Log.d("!!!!!잘못됨!!")
                    }

                })
    }
    fun phone_sms_send(){
        var phone : String
        phone  = "010" + dataBinding.etPhoneNum1.text + dataBinding.etPhoneNum2.text
        callApi(NetworkManager.getInstance(mContext)?.getApiService()?.Sendsms(phone,"join"), object : ApiResponseHandler<CommonResponse> {
            override fun onSuccess(result: CommonResponse?) {
                ToastUtil.show(mContext,"인증번호를 요청하였습니다.")
                button_select_sms = false
                Log.d("통신성공!!!!!!!!!!")
            }

            override fun onServerFail(result: CommonResponse?) {
                if(result?.message.toString().equals("등록된 핸드폰 번호 입니다."))
                    ToastUtil.show(mContext,"이미 사용중인 휴대폰번호입니다.")
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
    fun phone_sms_check(){
        var phone : String
        phone  = "010" + dataBinding.etPhoneNum1.text + dataBinding.etPhoneNum2.text
            callApi(NetworkManager.getInstance(mContext)?.getApiService()?.checksms(phone,dataBinding.etConfirmNum.text.toString()), object :
                ApiResponseHandler<CommonResponse> {
                override fun onSuccess(result: CommonResponse?) {
                    ToastUtil.show(mContext,"인증완료")
                    Log.d("통신성공!!!!!!!!!!")
                }

                override fun onServerFail(result: CommonResponse?) {
                    if(result?.message.toString().equals("confirm should not be empty"))
                        ToastUtil.show(mContext,"인증번호를 입력하여주세요.")
                    else
                        ToastUtil.show(mContext,result?.message)

                }

                override fun onNoResponse(result: CommonResponse?) {
                    Log.d("응답없음!!!!!!!")
                }

                override fun onBadRequest(t: Throwable?) {
                    Log.d("!!!!!잘못됨!!")
                }

            })
    }
    fun register(){
            var phone : String
            phone  = "010" + dataBinding.etPhoneNum1.text + dataBinding.etPhoneNum2.text
            val data  = join(dataBinding.etConfirmId.text.toString(),dataBinding.etPwd.text.toString(),phone,dataBinding.etNickname.text.toString())
            callApi(NetworkManager.getInstance(mContext)?.getApiService()?.join(data), object :
                ApiResponseHandler<CommonResponse> {
                override fun onSuccess(result: CommonResponse?) {
                    ToastUtil.show(mContext,"회원가입 완료하였습니다.")
                    Log.d("통신성공!!!!!!!!!!")
                    finishWithAnim()
                }

                override fun onServerFail(result: CommonResponse?) {
                    if(dataBinding.etConfirmId.text.isEmpty()) {
                        ToastUtil.show(mContext, "아이디를 입력하여주세요.")
                    }else if(dataBinding.etPwd.text.isEmpty()||dataBinding.etPwdConfirm.text.isEmpty()){
                        ToastUtil.show(mContext,"비밀번호를 확인하여주세요.")
                    }else if(id_same == false){
                        ToastUtil.show(mContext,"아이디 중복을 확인하여주세요.")
                    }else if(dataBinding.etPhoneNum2.text.isEmpty()||dataBinding.etPhoneNum1.text.isEmpty()){
                        ToastUtil.show(mContext,"휴대폰번호를 입력하여주세요.")
                    }else if(dataBinding.etConfirmNum.text.isEmpty()){
                        ToastUtil.show(mContext,"인증번호를 입력하여주세요.")
                    }else if(dataBinding.etNickname.text.isEmpty()){
                        ToastUtil.show(mContext,"닉네임을 입력하여주세요.")
                    }
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