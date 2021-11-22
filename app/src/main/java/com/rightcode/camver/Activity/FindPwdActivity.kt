package com.rightcode.camver.Activity

import android.content.Intent
import android.os.Bundle
import com.rightcode.camver.Dialog.OneminuteDialog
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.Util.ToastUtil
import com.rightcode.camver.databinding.ActivityFindPwdBinding
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.NetworkManager
import com.rightcode.camver.network.model.response.CommonResponse

class FindPwdActivity : BaseActivity<ActivityFindPwdBinding>() {
    var id :String =""
    var phone_num :String =""
    var start: Long = 0
    var button_select_sms: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_find_pwd)
    }

    override fun initActivity() {}
    override fun initClickListener() {
        dataBinding.tvCheckId.setOnClickListener {
            check_id()
        }
        dataBinding.tvPhoneNumber.setOnClickListener {
            if(button_select_sms==true){
                if(dataBinding.etPhoneNum1.text.isEmpty()&&dataBinding.etPhoneNum2.text.isEmpty()){
                    ToastUtil.show(mContext,"전화번호를 입력하여주세요")
                }else{
                    start = System.currentTimeMillis()
                    button_select_sms = false
                    send_sms()
                    ToastUtil.show(mContext,"인증번호를 요청하였습니다.")
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
            send_sms()
        }
        dataBinding.tvPhoneConfirm.setOnClickListener {
            check_phone()
        }
        dataBinding.tvStart.setOnClickListener {
            id = dataBinding.etId.text.toString()
            phone_num = "010"+dataBinding.etPhoneNum1.text.toString() + dataBinding.etPhoneNum2.text.toString()
            val intent = Intent(mContext, FindPwdOkActivity::class.java)
            intent.putExtra("Id",id)
            intent.putExtra("phone",phone_num)
            startActivity(intent)
            ToastUtil.show(mContext,"비밀번호 변경완료")
            finishWithAnim()
        }
    }
    fun send_sms(){
        var phone  = "010" + dataBinding.etPhoneNum1.text + dataBinding.etPhoneNum2.text
        callApi(NetworkManager.getInstance(mContext)?.getApiService()?.Sendsms(phone,"find"), object :
            ApiResponseHandler<CommonResponse> {
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
    fun check_phone(){
        var phone  = "010" + dataBinding.etPhoneNum1.text + dataBinding.etPhoneNum2.text
        callApi(NetworkManager.getInstance(mContext)?.getApiService()?.checksms(phone,dataBinding.etSmsCheck.text.toString()), object :
            ApiResponseHandler<CommonResponse> {
            override fun onSuccess(result: CommonResponse?) {
                Log.d("통신성공!!!!!!!!!!")
                ToastUtil.show(mContext,"인증완료")
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
    fun check_id(){
            callApi(NetworkManager.getInstance(mContext)?.getApiService()?.Idcheck(dataBinding.etId.text.toString()),
                object : ApiResponseHandler<CommonResponse> {
                    override fun onSuccess(result: CommonResponse?) {
                        Log.d("통신성공!!!!!!!!!!")
                        ToastUtil.show(mContext,result?.message)
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
}