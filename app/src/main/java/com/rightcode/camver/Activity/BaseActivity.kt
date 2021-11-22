package com.rightcode.camver.Activity

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import com.rightcode.camver.Dialog.LoadingDialog
import com.rightcode.camver.network.model.response.CommonResponse
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.network.ApiResponseHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class BaseActivity<T : ViewDataBinding>() : AppCompatActivity() {
    var layoutId: Int? = null
    lateinit var dataBinding: T

    private val EXIT_BACK_PRESSED_TIME = 2000;
    private val CLICK_TIME_INTERVAL = 500;
    private var mLastClickTime = System.currentTimeMillis()
    protected val RESPONSE_NULL_MSG = "서버로부터 응답이 존재하지 않습니다."

    protected var mLoadingDialog: LoadingDialog? = null
    protected var mErrorDialog = null

    protected lateinit var mContext: Context

    protected fun bindView(layoutId: Int) {
        mContext = this
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        initActivity()
        initClickListener()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    abstract fun initClickListener()

    abstract fun initActivity()

    override fun startActivity(intent : Intent) {
        val now = System.currentTimeMillis()

        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return
        }

        mLastClickTime = now

        super.startActivity(intent)
        overridePendingTransition(
            R.anim.slide_in_activity_from_right,
            R.anim.slide_out_activity_to_left
        )
    }

    fun finishWithAnim() {
        finish()
        overridePendingTransition(
            R.anim.slide_in_activity_from_left,
            R.anim.slide_out_activity_to_right
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_activity_from_left,
            R.anim.slide_out_activity_to_right
        )
    }

    protected fun showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(mContext)
        }

        if (mLoadingDialog?.isShowing != false) {
            return
        }

        mLoadingDialog?.show()
    }

    protected fun getDrawable2(resId: Int): Drawable? {
        return ContextCompat.getDrawable(mContext, resId)
    }


    protected fun <Q : CommonResponse?> callApi(call:Call<Q>?, handler: ApiResponseHandler<Q>) {
        call ?: return
        val callback = object : Callback<Q?> {
            override fun onResponse(call: Call<Q?>, response: Response<Q?>) {
                val result: Q? = response.body()
                if (response.code() == 200 || response.code() == 201) {
                    handler.onSuccess(result)
                } else if (response.code() > 201) {
                    if (response.body() != null) {
                        val error: CommonResponse? = response.body()
                        handler.onServerFail(error)
                        return
                    }
                    if (response.errorBody() == null) {
//                        showErrorDialog(getString(R.string.error_msg))
                        return
                    }
                    try {
                        val gson = Gson()
                        val error = gson.fromJson(
                            response.errorBody()!!.string(),
                            CommonResponse::class.java
                        )
                        handler.onServerFail(error)
                    } catch (e: Exception) {
                        Log.e(e.message)
                    }
                } else {
                    handler.onNoResponse(result)
                }
            }

            override fun onFailure(call: Call<Q?>, t: Throwable) {
                Log.e(t.message)
                handler.onBadRequest(t)
            }
        }

        call.enqueue(callback)
    }
}