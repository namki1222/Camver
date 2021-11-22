package com.rightcode.camver.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.google.gson.Gson
import com.rightcode.camver.Activity.BaseActivity
import com.rightcode.camver.R
import com.rightcode.camver.Util.Log
import com.rightcode.camver.network.ApiResponseHandler
import com.rightcode.camver.network.model.response.CommonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var dataBinding: T
    var activity: Activity? = null
    private val mLastClickTime = System.currentTimeMillis()
    protected fun bindView(layoutId: Int, inflater: LayoutInflater, container: ViewGroup?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        dataBinding.getRoot().let { ButterKnife.bind(this, it) }
        initBinding()
        initFragment()
        initClickListener()
        return dataBinding.getRoot()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
        }
    }

    override fun startActivity(intent: Intent) {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>?)?.startActivity(intent)
        }
    }

    protected abstract fun initBinding()
    protected abstract fun initFragment()
    protected abstract fun initClickListener()

    companion object {
        private const val CLICK_TIME_INTERVAL: Long = 300
        const val RESPONSE_NULL_MSG = "서버로부터 응답이 존재하지 않습니다."
    }
    protected fun <Q : CommonResponse?> callApi(call: Call<Q>?, handler: ApiResponseHandler<Q>) {
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